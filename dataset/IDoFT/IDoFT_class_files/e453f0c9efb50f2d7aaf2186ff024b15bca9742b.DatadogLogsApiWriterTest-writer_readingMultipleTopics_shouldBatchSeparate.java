/*
Unless explicitly stated otherwise all files in this repository are licensed under the Apache-2.0 License.
This product includes software developed at Datadog (https://www.datadoghq.com/). Copyright 2020 Datadog, Inc.
 */

package com.datadoghq.connect.logs.sink;

import com.datadoghq.connect.logs.sink.util.RequestInfo;
import com.datadoghq.connect.logs.sink.util.RestHelper;
import org.apache.kafka.connect.sink.SinkRecord;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DatadogLogsApiWriterTest {
    private DatadogLogsSinkConnectorConfig config;
    private DatadogLogsApiWriter writer;
    private Map<String, String> props;
    private List<SinkRecord> records;
    private RestHelper restHelper;

    @Before
    public void setUp() throws Exception {
        records = new ArrayList<>();
        props = new HashMap<>();
        props.put(DatadogLogsSinkConnectorConfig.DD_API_KEY, RestHelper.API_KEY);
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
        config = new DatadogLogsSinkConnectorConfig(false, "localhost:8080", 500, props);
        writer = new DatadogLogsApiWriter(config);

        records.add(new SinkRecord("someTopic", 0, null, "someKey", null, "someValue1", 0));
        writer.write(records);

        Assert.assertEquals(1, restHelper.getCapturedRequests().size());
        RequestInfo request = restHelper.getCapturedRequests().get(0);
        Assert.assertEquals("POST", request.getMethod());
        Assert.assertEquals("/v1/input/" + config.ddApiKey, request.getUrl());
        Assert.assertTrue(request.getHeaders().contains("Content-Type:application/json"));
        Assert.assertTrue(request.getHeaders().contains("Content-Encoding:gzip"));
    }

    @Test
    public void writer_batchAtMax_shouldSendBatched() throws IOException {
        config = new DatadogLogsSinkConnectorConfig(false, "localhost:8080", 2, props);
        writer = new DatadogLogsApiWriter(config);

        records.add(new SinkRecord("someTopic", 0, null, "someKey", null, "someValue1", 0));
        records.add(new SinkRecord("someTopic", 0, null, "someKey", null, "someValue2", 0));
        writer.write(records);

        Assert.assertEquals(1, restHelper.getCapturedRequests().size());

        RequestInfo request = restHelper.getCapturedRequests().get(0);
        Assert.assertEquals("[{\"message\":\"someValue1\",\"ddsource\":\"kafka-connect\",\"ddtags\":\"topic:someTopic\"},{\"message\":\"someValue2\",\"ddsource\":\"kafka-connect\",\"ddtags\":\"topic:someTopic\"}]", request.getBody());
    }

    @Test
    public void writer_batchAboveMax_shouldSendSeparate() throws IOException {
        config = new DatadogLogsSinkConnectorConfig(false, "localhost:8080", 1, props);
        writer = new DatadogLogsApiWriter(config);

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
        config = new DatadogLogsSinkConnectorConfig(false, "localhost:8080", 2, props);
        writer = new DatadogLogsApiWriter(config);

        records.add(new SinkRecord("someTopic1", 0, null, "someKey", null, "someValue1", 0));
        records.add(new SinkRecord("someTopic2", 0, null, "someKey", null, "someValue2", 0));
        writer.write(records);

        Assert.assertEquals(2, restHelper.getCapturedRequests().size());

        RequestInfo request1 = restHelper.getCapturedRequests().get(0);
        RequestInfo request2 = restHelper.getCapturedRequests().get(1);

        Assert.assertEquals("[{\"message\":\"someValue1\",\"ddsource\":\"kafka-connect\",\"ddtags\":\"topic:someTopic1\"}]", request2.getBody());
        Assert.assertEquals("[{\"message\":\"someValue2\",\"ddsource\":\"kafka-connect\",\"ddtags\":\"topic:someTopic2\"}]", request1.getBody());
    }

    @Test(expected = IOException.class)
    public void writer_givenError_shouldThrowException() throws IOException {
        props.put(DatadogLogsSinkConnectorConfig.DD_API_KEY, "invalidAPIKey");
        config = new DatadogLogsSinkConnectorConfig(false, "localhost:8080", 500, props);
        writer = new DatadogLogsApiWriter(config);

        records.add(new SinkRecord("someTopic", 0, null, "someKey", null, "someValue1", 0));
        writer.write(records);
    }

    @Test
    public void metadata_asOneBatch_shouldPopulatePerBatch() throws IOException {
        props.put(DatadogLogsSinkConnectorConfig.DD_TAGS, "team:agent-core, author:berzan");
        props.put(DatadogLogsSinkConnectorConfig.DD_HOSTNAME, "test-host");
        props.put(DatadogLogsSinkConnectorConfig.DD_SERVICE, "test-service");

        config = new DatadogLogsSinkConnectorConfig(false, "localhost:8080", 500, props);
        writer = new DatadogLogsApiWriter(config);

        records.add(new SinkRecord("someTopic", 0, null, "someKey", null, "someValue1", 0));
        records.add(new SinkRecord("someTopic", 0, null, "someKey", null, "someValue2", 0));
        writer.write(records);

        RequestInfo request = restHelper.getCapturedRequests().get(0);

        Assert.assertEquals("[{\"message\":\"someValue1\",\"ddsource\":\"kafka-connect\",\"ddtags\":\"topic:someTopic,team:agent-core,author:berzan\",\"hostname\":\"test-host\",\"service\":\"test-service\"},{\"message\":\"someValue2\",\"ddsource\":\"kafka-connect\",\"ddtags\":\"topic:someTopic,team:agent-core,author:berzan\",\"hostname\":\"test-host\",\"service\":\"test-service\"}]", request.getBody());
    }
}
