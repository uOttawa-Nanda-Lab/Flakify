/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.nifi.processors.splunk;

import com.splunk.RequestMessage;
import com.splunk.ResponseMessage;
import com.splunk.Service;
import com.splunk.ServiceArgs;
import org.apache.nifi.flowfile.FlowFile;
import org.apache.nifi.processor.ProcessContext;
import org.apache.nifi.processor.ProcessSession;
import org.apache.nifi.util.MockFlowFile;
import org.apache.nifi.util.TestRunner;
import org.apache.nifi.util.TestRunners;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class TestPutSplunkHTTP {
    private static final String ACK_ID = "1234";
    private static final String EVENT = "{\"a\"=\"á\",\"c\"=\"ő\",\"e\"=\"'ű'\"}"; // Intentionally uses UTF-8 character
    private static final String SUCCESS_RESPONSE =
            "{\n" +
            "    \"text\": \"Success\",\n" +
            "    \"code\": 0,\n" +
            "    \"ackId\": " + ACK_ID + "\n" +
            "}";
    private static final String FAILURE_RESPONSE = "{\n" +
            "    \"text\": \"Failure\",\n" +
            "    \"code\": 13\n" +
            "}";

    @Mock
    private Service service;

    @Mock
    private ResponseMessage response;

    @Captor
    private ArgumentCaptor<String> path;

    @Captor
    private ArgumentCaptor<RequestMessage> request;

    private MockedPutSplunkHTTP processor;
    private TestRunner testRunner;

    @Before
    public void setUp() {
        processor = new MockedPutSplunkHTTP(service);
        testRunner = TestRunners.newTestRunner(processor);
        testRunner.setProperty(SplunkAPICall.SCHEME, "http");
        testRunner.setProperty(SplunkAPICall.TOKEN, "Splunk 888c5a81-8777-49a0-a3af-f76e050ab5d9");
        testRunner.setProperty(SplunkAPICall.REQUEST_CHANNEL, "22bd7414-0d77-4c73-936d-c8f5d1b21862");

        Mockito.when(service.send(path.capture(), request.capture())).thenReturn(response);
    }

    @After
    public void tearDown() {
        testRunner.shutdown();
    }

    @Test
    public void testRunSuccess() throws Exception {
        // given
        givenSplunkReturnsWithSuccess();

        // when
        testRunner.enqueue(givenFlowFile());
        testRunner.run();

        // then
        testRunner.assertAllFlowFilesTransferred(PutSplunkHTTP.RELATIONSHIP_SUCCESS, 1);
        final MockFlowFile outgoingFlowFile = testRunner.getFlowFilesForRelationship(PutSplunkHTTP.RELATIONSHIP_SUCCESS).get(0);

        Assert.assertEquals(EVENT, outgoingFlowFile.getContent());
        Assert.assertEquals(ACK_ID, outgoingFlowFile.getAttribute("splunk.acknowledgement.id"));
        Assert.assertNotNull(outgoingFlowFile.getAttribute("splunk.responded.at"));
        Assert.assertEquals("200", outgoingFlowFile.getAttribute("splunk.status.code"));
        Assert.assertEquals("application/json", request.getValue().getHeader().get("Content-Type"));
    }

    @Test
    public void testHappyPathWithCustomQueryParameters() throws Exception {
        // given
        testRunner.setProperty(PutSplunkHTTP.SOURCE, "test_source");
        testRunner.setProperty(PutSplunkHTTP.SOURCE_TYPE, "test?source?type");
        givenSplunkReturnsWithSuccess();

        // when
        testRunner.enqueue(EVENT);
        testRunner.run();

        // then
        testRunner.assertAllFlowFilesTransferred(PutSplunkHTTP.RELATIONSHIP_SUCCESS, 1);
        Assert.assertEquals("/services/collector/raw?sourcetype=test%3Fsource%3Ftype&source=test_source", path.getValue());
    }

    @Test
    public void testHappyPathWithCustomQueryParametersFromFlowFile() throws Exception {
        // given
        testRunner.setProperty(PutSplunkHTTP.SOURCE, "${ff_source}");
        testRunner.setProperty(PutSplunkHTTP.SOURCE_TYPE, "${ff_source_type}");
        testRunner.setProperty(PutSplunkHTTP.HOST, "${ff_host}");
        testRunner.setProperty(PutSplunkHTTP.INDEX, "${ff_index}");
        testRunner.setProperty(PutSplunkHTTP.CHARSET, "${ff_charset}");
        testRunner.setProperty(PutSplunkHTTP.CONTENT_TYPE, "${ff_content_type}");
        givenSplunkReturnsWithSuccess();

        final Map<String, String> attributes = new HashMap<>();
        attributes.put("ff_source", "test_source");
        attributes.put("ff_source_type", "test?source?type");
        attributes.put("ff_host", "test_host");
        attributes.put("ff_index", "test_index");
        attributes.put("ff_charset", "UTF-8");
        attributes.put("ff_content_type", "test_content_type");

        final MockFlowFile incomingFlowFile = new MockFlowFile(1);
        incomingFlowFile.putAttributes(attributes);
        incomingFlowFile.setData(EVENT.getBytes(StandardCharsets.UTF_8));

        // when
        testRunner.enqueue(incomingFlowFile);
        testRunner.run();

        // then
        testRunner.assertAllFlowFilesTransferred(PutSplunkHTTP.RELATIONSHIP_SUCCESS, 1);
        Assert.assertEquals("/services/collector/raw?host=test_host&index=test_index&sourcetype=test%3Fsource%3Ftype&source=test_source", path.getValue());

        Assert.assertEquals(EVENT, processor.getLastContent());
        Assert.assertEquals(attributes.get("ff_content_type"), processor.getLastContentType());
    }

    @Test
    public void testHappyPathWithContentType() throws Exception {
        // given
        testRunner.setProperty(PutSplunkHTTP.CONTENT_TYPE, "text/xml");
        givenSplunkReturnsWithSuccess();

        // when
        testRunner.enqueue(givenFlowFile());
        testRunner.run();

        // then
        testRunner.assertAllFlowFilesTransferred(PutSplunkHTTP.RELATIONSHIP_SUCCESS, 1);
        Assert.assertEquals("text/xml", request.getValue().getHeader().get("Content-Type"));
    }

    @Test
    public void testSplunkCallFailure() throws Exception {
        // given
        givenSplunkReturnsWithFailure();

        // when
        testRunner.enqueue(givenFlowFile());
        testRunner.run();

        // then
        testRunner.assertAllFlowFilesTransferred(PutSplunkHTTP.RELATIONSHIP_FAILURE, 1);
        final MockFlowFile outgoingFlowFile = testRunner.getFlowFilesForRelationship(PutSplunkHTTP.RELATIONSHIP_FAILURE).get(0);

        Assert.assertEquals(EVENT, outgoingFlowFile.getContent());
        Assert.assertNull(outgoingFlowFile.getAttribute("splunk.acknowledgement.id"));
        Assert.assertNull(outgoingFlowFile.getAttribute("splunk.responded.at"));
        Assert.assertEquals("200", outgoingFlowFile.getAttribute("splunk.status.code"));
        Assert.assertEquals("13", outgoingFlowFile.getAttribute("splunk.response.code"));
    }

    @Test
    public void testSplunkApplicationFailure() throws Exception {
        // given
        givenSplunkReturnsWithApplicationFailure(403);

        // when
        testRunner.enqueue(givenFlowFile());
        testRunner.run();

        // then
        testRunner.assertAllFlowFilesTransferred(PutSplunkHTTP.RELATIONSHIP_FAILURE, 1);
        final MockFlowFile outgoingFlowFile = testRunner.getFlowFilesForRelationship(PutSplunkHTTP.RELATIONSHIP_FAILURE).get(0);

        Assert.assertEquals(EVENT, outgoingFlowFile.getContent());
        Assert.assertNull(outgoingFlowFile.getAttribute("splunk.acknowledgement.id"));
        Assert.assertNull(outgoingFlowFile.getAttribute("splunk.responded.at"));
        Assert.assertNull(outgoingFlowFile.getAttribute("splunk.response.code"));
        Assert.assertEquals("403", outgoingFlowFile.getAttribute("splunk.status.code"));
    }


    private MockFlowFile givenFlowFile() throws UnsupportedEncodingException {
        final MockFlowFile result = new MockFlowFile(System.currentTimeMillis());
        result.setData(EVENT.getBytes("UTF-8"));
        result.putAttributes(Collections.singletonMap("mime.type", "application/json"));
        return result;
    }

    private void givenSplunkReturnsWithSuccess() throws Exception {
        final InputStream inputStream = new ByteArrayInputStream(SUCCESS_RESPONSE.getBytes("UTF-8"));
        Mockito.when(response.getStatus()).thenReturn(200);
        Mockito.when(response.getContent()).thenReturn(inputStream);
    }

    private void givenSplunkReturnsWithFailure() throws Exception {
        final InputStream inputStream = new ByteArrayInputStream(FAILURE_RESPONSE.getBytes("UTF-8"));
        Mockito.when(response.getStatus()).thenReturn(200);
        Mockito.when(response.getContent()).thenReturn(inputStream);
    }

    private void givenSplunkReturnsWithApplicationFailure(int code) throws Exception {
        final InputStream inputStream = new ByteArrayInputStream("non-json-content".getBytes("UTF-8"));
        Mockito.when(response.getStatus()).thenReturn(code);
        Mockito.when(response.getContent()).thenReturn(inputStream);
    }

    public static class MockedPutSplunkHTTP extends PutSplunkHTTP {
        final Service serviceMock;
        Object lastContent = null;
        String lastContentType = null;

        public MockedPutSplunkHTTP(final Service serviceMock) {
            this.serviceMock = serviceMock;
        }

        @Override
        protected Service getSplunkService(final ServiceArgs splunkServiceArguments) {
            return serviceMock;
        }

        @Override
        protected RequestMessage createRequestMessage(final ProcessSession session, final FlowFile flowFile, final ProcessContext context) {
            final RequestMessage requestMessage = super.createRequestMessage(session, flowFile, context);
            lastContent = requestMessage.getContent();
            lastContentType = requestMessage.getHeader().get("Content-Type");
            return requestMessage;
        }

        public Object getLastContent() {
            return lastContent;
        }

        public String getLastContentType() {
            return lastContentType;
        }
    }
}
