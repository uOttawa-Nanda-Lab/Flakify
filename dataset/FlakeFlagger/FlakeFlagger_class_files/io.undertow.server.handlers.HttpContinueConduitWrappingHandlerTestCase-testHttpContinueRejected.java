/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2012 Red Hat, Inc., and individual contributors
 * as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.undertow.server.handlers;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import io.undertow.server.protocol.http.HttpContinue;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.testutils.AjpIgnore;
import io.undertow.testutils.DefaultServer;
import io.undertow.testutils.HttpClientUtils;
import io.undertow.testutils.TestHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * @author Stuart Douglas
 */
@RunWith(DefaultServer.class)
@AjpIgnore
public class HttpContinueConduitWrappingHandlerTestCase {

    private static volatile boolean accept = false;

    @BeforeClass
    public static void setup() {
        final BlockingHandler blockingHandler = new BlockingHandler();
        final HttpContinueReadHandler handler = new HttpContinueReadHandler(blockingHandler);
        DefaultServer.setRootHandler(handler);
        blockingHandler.setRootHandler(new HttpHandler() {
            @Override
            public void handleRequest(final HttpServerExchange exchange) {
                try {
                    if(!accept) {
                        HttpContinue.rejectExchange(exchange);
                        return;
                    }
                    byte[] buffer = new byte[1024];
                    final ByteArrayOutputStream b = new ByteArrayOutputStream();
                    int r = 0;
                    final OutputStream outputStream = exchange.getOutputStream();
                    final InputStream inputStream =  exchange.getInputStream();
                    while ((r = inputStream.read(buffer)) > 0) {
                        b.write(buffer, 0, r);
                    }
                    outputStream.write(b.toByteArray());
                    outputStream.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
    }

    @Test
    public void testHttpContinueRejected() throws IOException {
        accept = false;
        String message = "My HTTP Request!";
        HttpParams httpParams = new BasicHttpParams();
        httpParams.setParameter("http.protocol.wait-for-continue", Integer.MAX_VALUE);

        TestHttpClient client = new TestHttpClient();
        client.setParams(httpParams);
        try {
            HttpPost post = new HttpPost(DefaultServer.getDefaultServerURL() + "/path");
            post.addHeader("Expect", "100-continue");
            post.setEntity(new StringEntity(message));

            HttpResponse result = client.execute(post);
            Assert.assertEquals(417, result.getStatusLine().getStatusCode());
        } finally {
            client.getConnectionManager().shutdown();
        }
    }

}
