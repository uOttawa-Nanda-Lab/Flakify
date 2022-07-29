/*
 * ====================================================================
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 * ====================================================================
 *
 * This software consists of voluntary contributions made by many
 * individuals on behalf of the Apache Software Foundation.  For more
 * information on the Apache Software Foundation, please see
 * <http://www.apache.org/>.
 *
 */

package org.apache.http.protocol;

import java.io.IOException;

import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolException;
import org.apache.http.message.BasicHttpEntityEnclosingRequest;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.message.BasicHttpResponse;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

public class TestHttpRequestExecutor {


    @Test
    public void testInvalidInput() throws Exception {
        HttpContext context = new BasicHttpContext();
        HttpRequest request = new BasicHttpRequest("GET", "/");
        HttpResponse response = new BasicHttpResponse(HttpVersion.HTTP_1_1, 200, "OK");
        HttpClientConnection conn = Mockito.mock(HttpClientConnection.class);
        HttpProcessor httprocessor = Mockito.mock(HttpProcessor.class);
        try {
            HttpRequestExecutor executor = new HttpRequestExecutor();
            executor.execute(null, conn, context);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
        try {
            HttpRequestExecutor executor = new HttpRequestExecutor();
            executor.execute(request, null, context);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
        try {
            HttpRequestExecutor executor = new HttpRequestExecutor();
            executor.execute(request, conn, null);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }

        try {
            HttpRequestExecutor executor = new HttpRequestExecutor();
            executor.doSendRequest(null, conn, context);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
        try {
            HttpRequestExecutor executor = new HttpRequestExecutor();
            executor.doSendRequest(request, null, context);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
        try {
            HttpRequestExecutor executor = new HttpRequestExecutor();
            executor.doSendRequest(request, conn, null);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }

        try {
            HttpRequestExecutor executor = new HttpRequestExecutor();
            executor.doReceiveResponse(null, conn, context);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
        try {
            HttpRequestExecutor executor = new HttpRequestExecutor();
            executor.doReceiveResponse(request, null, context);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
        try {
            HttpRequestExecutor executor = new HttpRequestExecutor();
            executor.doReceiveResponse(request, conn, null);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }

        try {
            HttpRequestExecutor executor = new HttpRequestExecutor();
            executor.preProcess(null, httprocessor, context);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
        try {
            HttpRequestExecutor executor = new HttpRequestExecutor();
            executor.preProcess(request, null, context);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
        try {
            HttpRequestExecutor executor = new HttpRequestExecutor();
            executor.preProcess(request, httprocessor, null);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }

        try {
            HttpRequestExecutor executor = new HttpRequestExecutor();
            executor.postProcess(null, httprocessor, context);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
        try {
            HttpRequestExecutor executor = new HttpRequestExecutor();
            executor.postProcess(response, null, context);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
        try {
            HttpRequestExecutor executor = new HttpRequestExecutor();
            executor.postProcess(response, httprocessor, null);
            Assert.fail("IllegalArgumentException expected");
        } catch (IllegalArgumentException expected) {
        }
    }

}
