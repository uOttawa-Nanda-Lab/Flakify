/*
Copyright 2012 Twitter, Inc.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/
package com.twitter.hraven;

import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseTestingUtility;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Table;
import org.codehaus.jackson.map.DeserializationConfig;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.type.TypeReference;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.base.Predicate;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import com.twitter.hraven.datasource.HRavenTestUtil;
import com.twitter.hraven.datasource.JobHistoryByIdService;
import com.twitter.hraven.datasource.JobHistoryService;
import com.twitter.hraven.rest.ObjectMapperProvider;
import com.twitter.hraven.rest.RestResource;
import com.twitter.hraven.rest.SerializationContext;
import com.twitter.hraven.util.JSONUtil;

/**
 * Tests that we can deserialize json and serialize it again and get the same
 * results. Written so we can swap in new JSON content from the API and verify
 * that serde still works.
 *
 */
@SuppressWarnings("deprecation")
public class TestJsonSerde {
  @SuppressWarnings("unused")
  private static final Log LOG = LogFactory.getLog(TestJsonSerde.class);
  private static HBaseTestingUtility UTIL;
  private static JobHistoryByIdService idService;

  private static Connection hbaseConnection = null;

  @BeforeClass
  public static void setupBeforeClass() throws Exception {
    UTIL = new HBaseTestingUtility();
    UTIL.startMiniCluster();
    HRavenTestUtil.createSchema(UTIL);

    hbaseConnection =
        ConnectionFactory.createConnection(UTIL.getConfiguration());
    idService =
        new JobHistoryByIdService(hbaseConnection);
  }

  @Test
  public void testJsonSerializationFlowStatsJobDetails() throws Exception {

    // load a sample flow
    final short numJobsAppOne = 3;
    final short numJobsAppTwo = 4;
    final long baseStats = 10L;
    Table historyTable =
        hbaseConnection.getTable(TableName.valueOf(Constants.HISTORY_TABLE));
    GenerateFlowTestData flowDataGen = new GenerateFlowTestData();
    flowDataGen.loadFlow("c1@local", "buser", "AppOne", 1234, "a",
        numJobsAppOne, baseStats, idService, historyTable);
    flowDataGen.loadFlow("c2@local", "Muser", "AppTwo", 2345, "b",
        numJobsAppTwo, baseStats, idService, historyTable);
    historyTable.close();
    JobHistoryService service =
        new JobHistoryService(UTIL.getConfiguration(), hbaseConnection);
    List<Flow> actualFlows = service.getFlowTimeSeriesStats("c1@local", "buser",
        "AppOne", "", 0L, 0L, 1000, null);

    // serialize flows into json
    ObjectMapper om = ObjectMapperProvider.createCustomMapper();
    om.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
    om.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
    om.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
        false);
    ByteArrayOutputStream f = new ByteArrayOutputStream();
    om.writeValue(f, actualFlows);
    ByteArrayInputStream is = new ByteArrayInputStream(f.toByteArray());
    @SuppressWarnings("unchecked")
    List<Flow> deserFlows =
        (List<Flow>) JSONUtil.readJson(is, new TypeReference<List<Flow>>() {
        });
    assertFlowDetails(actualFlows, deserFlows);

  }

  @Test
  public void testSerializationContext() throws Exception {

    // load a sample flow
    final short numJobs = 3;

    GenerateFlowTestData flowDataGen = new GenerateFlowTestData();
    // custom config to test out filtering of specific properties
    Map<String, String> fullConfig = Maps.newHashMap();
    fullConfig.put("name", "first");
    fullConfig.put("shortprop", "brief");
    fullConfig.put("longprop",
        "an extended bit of text that we will want to filter out from results");
    List<String> serializedKeys = Lists.newArrayList("name", "shortprop");

    Table historyTable =
        hbaseConnection.getTable(TableName.valueOf(Constants.HISTORY_TABLE));
    flowDataGen.loadFlow("c1@local", "buser", "testSerializationContext", 1234,
        "a", numJobs, 10, idService, historyTable, fullConfig);
    historyTable.close();

    JobHistoryService service =
        new JobHistoryService(UTIL.getConfiguration(), hbaseConnection);
    Flow actualFlow = service.getFlow("c1@local", "buser",
        "testSerializationContext", 1234, false);

    assertNotNull(actualFlow);
    Configuration actualConfig = actualFlow.getJobs().get(0).getConfiguration();
    assertEquals(fullConfig.get("name"), actualConfig.get("name"));
    assertEquals(fullConfig.get("shortprop"), actualConfig.get("shortprop"));
    assertEquals(fullConfig.get("longprop"), actualConfig.get("longprop"));

    // test serialization matching specific property keys
    // serialize flow into json
    RestResource.serializationContext.set(
        new SerializationContext(SerializationContext.DetailLevel.EVERYTHING,
            new SerializationContext.FieldNameFilter(serializedKeys), null,
            null, null));
    ObjectMapper om = ObjectMapperProvider.createCustomMapper();
    om.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
    om.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
    om.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
        false);
    ByteArrayOutputStream f = new ByteArrayOutputStream();
    om.writeValue(f, actualFlow);
    ByteArrayInputStream is = new ByteArrayInputStream(f.toByteArray());
    Flow deserFlow = (Flow) JSONUtil.readJson(is, new TypeReference<Flow>() {
    });
    assertFlowEquals(actualFlow, deserFlow);
    // only config properties in serializedKeys should be present in the
    // deserialized flow
    Configuration deserConfig = deserFlow.getJobs().get(0).getConfiguration();
    assertEquals(fullConfig.get("name"), deserConfig.get("name"));
    assertEquals(fullConfig.get("shortprop"), deserConfig.get("shortprop"));
    // longprop should not have been serialized
    assertNull(deserConfig.get("longprop"));

    // test serialization matching property regexes
    List<String> patterns = Lists.newArrayList("^.*prop$");
    RestResource.serializationContext.set(
        new SerializationContext(SerializationContext.DetailLevel.EVERYTHING,
            new SerializationContext.RegexConfigurationFilter(patterns), null,
            null, null));
    om = ObjectMapperProvider.createCustomMapper();
    om.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
    om.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
    om.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
        false);
    f = new ByteArrayOutputStream();
    om.writeValue(f, actualFlow);
    is = new ByteArrayInputStream(f.toByteArray());
    deserFlow = (Flow) JSONUtil.readJson(is, new TypeReference<Flow>() {
    });
    assertFlowEquals(actualFlow, deserFlow);
    // only config properties in serializedKeys should be present in the
    // deserialized flow
    deserConfig = deserFlow.getJobs().get(0).getConfiguration();
    // only 2 *prop keys should be present
    assertNull(deserConfig.get("name"));
    assertEquals(fullConfig.get("shortprop"), deserConfig.get("shortprop"));
    assertEquals(fullConfig.get("longprop"), deserConfig.get("longprop"));
  }

  @Test
  public void testJobSerializationContextFilters() throws Exception {
    // load a sample flow
    final short numJobs = 1;

    GenerateFlowTestData flowDataGen = new GenerateFlowTestData();
    Table historyTable =
        hbaseConnection.getTable(TableName.valueOf(Constants.HISTORY_TABLE));
    flowDataGen.loadFlow("c1@local", "buser", "testJobSerializationContextFilters", 1234,
        "a", numJobs, 10, idService, historyTable);
    historyTable.close();

    JobHistoryService service =
        new JobHistoryService(UTIL.getConfiguration(), hbaseConnection);
    Flow actualFlow = service.getFlow("c1@local", "buser",
        "testJobSerializationContextFilters", 1234, false);
    assertNotNull(actualFlow);
    assertTrue(actualFlow.getJobCount() == numJobs);
    JobDetails actualJob = actualFlow.getJobs().get(0);

    // test serialization matching specific property keys
    // serialize job into json
    List<String> includeFields = Lists.newArrayList("megabyteMillis", "jobId", "jobKey", "status", "counters");
    Predicate<String> includeFilter = new SerializationContext.FieldNameFilter(includeFields);
    SerializationContext serializationContext =
        new SerializationContext(SerializationContext.DetailLevel.EVERYTHING,
        null, null,
        includeFilter, null, null);
    RestResource.serializationContext.set(
        serializationContext);

    ObjectMapper om = ObjectMapperProvider.createCustomMapper();
    om.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
    om.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
    om.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
        false);
    ByteArrayOutputStream f = new ByteArrayOutputStream();
    om.writeValue(f, actualJob);
    ByteArrayInputStream is = new ByteArrayInputStream(f.toByteArray());
    JobDetails deserJob = (JobDetails) JSONUtil.readJson(is, new TypeReference<JobDetails>() {
    });

    // check the values match
    assertEquals(actualJob.getJobId(), deserJob.getJobId());
    assertEquals(actualJob.getJobKey(), deserJob.getJobKey());
    assertEquals(actualJob.getStatus(), deserJob.getStatus());
    assertEquals(actualJob.getMegabyteMillis(), deserJob.getMegabyteMillis());

    // check all counters are here
    assertEquals(actualJob.getCounters().getGroups(), deserJob.getCounters().getGroups());
    for (String group : actualJob.getCounters().getGroups()) {
      assertEquals(actualJob.getCounters().getGroup(group), deserJob.getCounters().getGroup(group));
    }
  }

  @Test
  public void testJobSerializationContextCounterFilters() throws Exception {
    // load a sample flow
    final short numJobs = 1;

    GenerateFlowTestData flowDataGen = new GenerateFlowTestData();
    Table historyTable =
        hbaseConnection.getTable(TableName.valueOf(Constants.HISTORY_TABLE));
    flowDataGen.loadFlow("c1@local", "buser", "testJobSerializationContextCounterFilters", 1234,
        "a", numJobs, 10, idService, historyTable);
    historyTable.close();

    JobHistoryService service =
        new JobHistoryService(UTIL.getConfiguration(), hbaseConnection);
    Flow actualFlow = service.getFlow("c1@local", "buser",
        "testJobSerializationContextCounterFilters", 1234, false);
    assertNotNull(actualFlow);
    assertTrue(actualFlow.getJobCount() == numJobs);
    JobDetails actualJob = actualFlow.getJobs().get(0);

    // test serialization matching specific property keys
    // serialize job into json
    List<String> includeFields = Lists.newArrayList("megabyteMillis", "jobId", "jobKey", "status");
    List<String> includeCounters = Lists.newArrayList("FileSystemCounters.HDFS_BYTES_READ", "FileSystemCounters.HDFS_BYTES_WRITTEN");
    Predicate<String> includeFilter = new SerializationContext.FieldNameFilter(includeFields);
    Predicate<String> includeCounterPredicate = new SerializationContext.FieldNameFilter(includeCounters);

    SerializationContext serializationContext =
        new SerializationContext(SerializationContext.DetailLevel.EVERYTHING,
            null, null,
            includeFilter, null, includeCounterPredicate);
    RestResource.serializationContext.set(
        serializationContext);

    ObjectMapper om = ObjectMapperProvider.createCustomMapper();
    om.configure(SerializationConfig.Feature.INDENT_OUTPUT, true);
    om.configure(SerializationConfig.Feature.FAIL_ON_EMPTY_BEANS, false);
    om.configure(DeserializationConfig.Feature.FAIL_ON_UNKNOWN_PROPERTIES,
        false);
    ByteArrayOutputStream f = new ByteArrayOutputStream();
    om.writeValue(f, actualJob);
    ByteArrayInputStream is = new ByteArrayInputStream(f.toByteArray());
    JobDetails deserJob = (JobDetails) JSONUtil.readJson(is, new TypeReference<JobDetails>() {
    });

    // check the values match
    assertEquals(actualJob.getJobId(), deserJob.getJobId());
    assertEquals(actualJob.getJobKey(), deserJob.getJobKey());
    assertEquals(actualJob.getStatus(), deserJob.getStatus());
    assertEquals(actualJob.getMegabyteMillis(), deserJob.getMegabyteMillis());

    // check all FileSystem counters are here
    assertEquals(Sets.newHashSet("FileSystemCounters"), deserJob.getCounters().getGroups());
    assertEquals(actualJob.getCounters().getGroup("FileSystemCounters"), deserJob.getCounters().getGroup("FileSystemCounters"));
  }

  private void assertFlowDetails(List<Flow> flow1, List<Flow> flow2) {
    assertNotNull(flow1);
    assertNotNull(flow2);
    assertEquals(flow1.size(), flow2.size());
    assertTrue(flow1.equals(flow2));
    for (int i = 0; i < flow1.size(); i++) {
      assertFlowEquals(flow1.get(i), flow2.get(i));
    }
  }

  private void assertFlowEquals(Flow flow1, Flow flow2) {
    assertEquals(flow1.getJobCount(), flow2.getJobCount());
    assertEquals(flow1.getJobs(), flow2.getJobs());
    assertEquals(flow1.getAppId(), flow2.getAppId());
    assertEquals(flow1.getCluster(), flow2.getCluster());
    assertEquals(flow1.getSubmitTime(), flow2.getSubmitTime());
    assertEquals(flow1.getDuration(), flow2.getDuration());
    assertEquals(flow1.getWallClockTime(), flow2.getWallClockTime());
    assertEquals(flow1.getRunId(), flow2.getRunId());
    assertEquals(flow1.getMapSlotMillis(), flow2.getMapSlotMillis());
    assertEquals(flow1.getReduceSlotMillis(), flow2.getReduceSlotMillis());
    assertEquals(flow1.getMegabyteMillis(), flow2.getMegabyteMillis());
    assertEquals(flow1.getHdfsBytesRead(), flow2.getHdfsBytesRead());
    assertEquals(flow1.getHdfsBytesWritten(), flow2.getHdfsBytesWritten());
    assertEquals(flow1.getJobGraphJSON(), flow2.getJobGraphJSON());
    assertEquals(flow1.getMapFileBytesRead(), flow2.getMapFileBytesRead());
    assertEquals(flow1.getMapFileBytesWritten(),
        flow2.getMapFileBytesWritten());
    assertEquals(flow1.getReduceFileBytesRead(),
        flow2.getReduceFileBytesRead());
    assertEquals(flow1.getTotalMaps(), flow2.getTotalMaps());
    assertEquals(flow1.getTotalReduces(), flow2.getTotalReduces());
    assertEquals(flow1.getVersion(), flow2.getVersion());
    assertEquals(flow1.getHadoopVersion(), flow2.getHadoopVersion());
    assertEquals(flow1.getUserName(), flow2.getUserName());
    assertJobListEquals(flow1.getJobs(), flow2.getJobs());
  }

  private void assertJobListEquals(List<JobDetails> job1,
      List<JobDetails> job2) {
    assertNotNull(job1);
    assertNotNull(job2);
    assertEquals(job1.size(), job2.size());

    for (int j = 0; j < job1.size(); j++) {
      assertEquals(job1.get(j).getJobId(), job2.get(j).getJobId());
      assertEquals(job1.get(j).getJobKey(), job2.get(j).getJobKey());
      assertEquals(job1.get(j).getMapFileBytesRead(),
          job2.get(j).getMapFileBytesRead());
      assertEquals(job1.get(j).getMapFileBytesWritten(),
          job2.get(j).getMapFileBytesWritten());
      assertEquals(job1.get(j).getReduceFileBytesRead(),
          job2.get(j).getReduceFileBytesRead());
      assertEquals(job1.get(j).getHdfsBytesRead(),
          job2.get(j).getHdfsBytesRead());
      assertEquals(job1.get(j).getHdfsBytesWritten(),
          job2.get(j).getHdfsBytesWritten());
      assertEquals(job1.get(j).getRunTime(), job2.get(j).getRunTime());
      assertEquals(job1.get(j).getMapSlotMillis(),
          job2.get(j).getMapSlotMillis());
      assertEquals(job1.get(j).getReduceSlotMillis(),
          job2.get(j).getReduceSlotMillis());
      assertEquals(job1.get(j).getMegabyteMillis(),
          job2.get(j).getMegabyteMillis());
      assertEquals(job1.get(j).getHadoopVersion(),
          job2.get(j).getHadoopVersion());
      assertEquals(job1.get(j).getUser(), job2.get(j).getUser());
    }
  }

  public static void tearDownAfterClass() throws Exception {
    try {
      if (hbaseConnection != null) {
        hbaseConnection.close();
      }
    } finally {
      UTIL.shutdownMiniCluster();
    }
  }
}
