/*
Unless explicitly stated otherwise all files in this repository are licensed under the Apache-2.0 License.
This product includes software developed at Datadog (https://www.datadoghq.com/). Copyright 2020 Datadog, Inc.
 */

package com.datadoghq.connect.logs.sink;

import com.datadoghq.connect.logs.sink.util.RequestInfo;
import com.datadoghq.connect.logs.sink.util.RestHelper;
import com.datadoghq.connect.logs.util.Project;

import org.apache.kafka.connect.sink.SinkRecord;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class DatadogLogsApiWriterTest {
    private Map<String, String> props;
    private List<SinkRecord> records;
    private RestHelper restHelper;
    private static String apiKey = "API_KEY";

    @Before
    public void setUp() throws Exception {
        records = new ArrayList<>();
        props = new HashMap<>();
        props.put(DatadogLogsSinkConnectorConfig.DD_API_KEY, apiKey);
        props.put(DatadogLogsSinkConnectorConfig.DD_URL, "localhost:8080");
        restHelper = new RestHelper();
        restHelper.start();
    }

    @After
    public void tearDown() throws Exception {
        restHelper.stop();
        restHelper.flushCapturedRequests();
    }

    @Test
    public void writer_givenConfigs_sendsPOSTToURL() throws IOException {
        DatadogLogsSinkConnectorConfig config = new DatadogLogsSinkConnectorConfig(false, 500, props);
        DatadogLogsApiWriter writer = new DatadogLogsApiWriter(config);

        records.add(new SinkRecord("someTopic", 0, null, "someKey", null, "someValue1", 0));
        writer.write(records);

        Assert.assertEquals(1, restHelper.getCapturedRequests().size());
        RequestInfo request = restHelper.getCapturedRequests().get(0);
        Assert.assertEquals("POST", request.getMethod());
        Assert.assertEquals("/api/v2/logs", request.getUrl());
        Assert.assertTrue(request.getHeaders().contains("Content-Type:application/json"));
        Assert.assertTrue(request.getHeaders().contains("Content-Encoding:gzip"));
        Assert.assertTrue(request.getHeaders().contains("DD-API-KEY:" + apiKey));
        Assert.assertTrue(request.getHeaders().contains("DD-EVP-ORIGIN:datadog-kafka-connect-logs"));
        Assert.assertTrue(request.getHeaders().contains("DD-EVP-ORIGIN-VERSION:" + Project.getVersion()));
    }

    @Test
    public void writer_batchAtMax_shouldSendBatched() throws IOException {
        DatadogLogsSinkConnectorConfig config = new DatadogLogsSinkConnectorConfig(false, 2, props);
        DatadogLogsApiWriter writer = new DatadogLogsApiWriter(config);

        records.add(new SinkRecord("someTopic", 0, null, "someKey", null, "someValue1", 0));
        records.add(new SinkRecord("someTopic", 0, null, "someKey", null, "someValue2", 0));
        writer.write(records);

        Assert.assertEquals(1, restHelper.getCapturedRequests().size());

        RequestInfo request = restHelper.getCapturedRequests().get(0);
        Assert.assertEquals("[{\"message\":\"someValue1\",\"ddsource\":\"kafka-connect\",\"ddtags\":\"topic:someTopic\"},{\"message\":\"someValue2\",\"ddsource\":\"kafka-connect\",\"ddtags\":\"topic:someTopic\"}]", request.getBody());
    }

    @Test
    public void writer_batchAboveMax_shouldSendSeparate() throws IOException {
        DatadogLogsSinkConnectorConfig config = new DatadogLogsSinkConnectorConfig(false, 1, props);
        DatadogLogsApiWriter writer = new DatadogLogsApiWriter(config);

        records.add(new SinkRecord("someTopic", 0, null, "someKey", null, "someValue1", 0));
        records.add(new SinkRecord("someTopic", 0, null, "someKey", null, "someValue2", 0));
        writer.write(records);

        Assert.assertEquals(2, restHelper.getCapturedRequests().size());

        RequestInfo request1 = restHelper.getCapturedRequests().get(0);
        RequestInfo request2 = restHelper.getCapturedRequests().get(1);

        Assert.assertEquals("[{\"message\":\"someValue1\",\"ddsource\":\"kafka-connect\",\"ddtags\":\"topic:someTopic\"}]", request1.getBody());
        Assert.assertEquals("[{\"message\":\"someValue2\",\"ddsource\":\"kafka-connect\",\"ddtags\":\"topic:someTopic\"}]", request2.getBody());
    }

    @Test
    public void writer_readingMultipleTopics_shouldBatchSeparate() throws IOException {
        DatadogLogsSinkConnectorConfig config = new DatadogLogsSinkConnectorConfig(false, 2, props);
        DatadogLogsApiWriter writer = new DatadogLogsApiWriter(config);

        records.add(new SinkRecord("someTopic1", 0, null, "someKey", null, "someValue1", 0));
        records.add(new SinkRecord("someTopic2", 0, null, "someKey", null, "someValue2", 0));
        writer.write(records);

        Assert.assertEquals(2, restHelper.getCapturedRequests().size());

        RequestInfo request1 = restHelper.getCapturedRequests().get(0);
        RequestInfo request2 = restHelper.getCapturedRequests().get(1);

        Set<String> requestBodySetActual = new HashSet<>();
        requestBodySetActual.add(request1.getBody());
        requestBodySetActual.add(request2.getBody());
        Set<String> requestBodySetExpected = new HashSet<>();
        requestBodySetExpected.add("[{\"message\":\"someValue1\",\"ddsource\":\"kafka-connect\",\"ddtags\":\"topic:someTopic1\"}]");
        requestBodySetExpected.add("[{\"message\":\"someValue2\",\"ddsource\":\"kafka-connect\",\"ddtags\":\"topic:someTopic2\"}]");
        Assert.assertEquals(requestBodySetExpected, requestBodySetActual);
    }

    @Test(expected = IOException.class)
    public void writer_IOException_for_status_429() throws Exception {
        DatadogLogsSinkConnectorConfig config = new DatadogLogsSinkConnectorConfig(false, 500, props);
        DatadogLogsApiWriter writer = new DatadogLogsApiWriter(config);

        restHelper.setHttpStatusCode(429);
        records.add(new SinkRecord("someTopic", 0, null, "someKey", null, "someValue1", 0));        
        writer.write(records);
    }

    @Test
    public void metadata_asOneBatch_shouldPopulatePerBatch() throws IOException {
        props.put(DatadogLogsSinkConnectorConfig.DD_TAGS, "team:agent-core, author:berzan");
        props.put(DatadogLogsSinkConnectorConfig.DD_HOSTNAME, "test-host");
        props.put(DatadogLogsSinkConnectorConfig.DD_SERVICE, "test-service");

        DatadogLogsSinkConnectorConfig config = new DatadogLogsSinkConnectorConfig(false, 500, props);
        DatadogLogsApiWriter writer = new DatadogLogsApiWriter(config);

        records.add(new SinkRecord("someTopic", 0, null, "someKey", null, "someValue1", 0));
        records.add(new SinkRecord("someTopic", 0, null, "someKey", null, "someValue2", 0));
        writer.write(records);

        RequestInfo request = restHelper.getCapturedRequests().get(0);

        Assert.assertEquals("[{\"message\":\"someValue1\",\"ddsource\":\"kafka-connect\",\"ddtags\":\"topic:someTopic,team:agent-core,author:berzan\",\"hostname\":\"test-host\",\"service\":\"test-service\"},{\"message\":\"someValue2\",\"ddsource\":\"kafka-connect\",\"ddtags\":\"topic:someTopic,team:agent-core,author:berzan\",\"hostname\":\"test-host\",\"service\":\"test-service\"}]", request.getBody());
    }
}
