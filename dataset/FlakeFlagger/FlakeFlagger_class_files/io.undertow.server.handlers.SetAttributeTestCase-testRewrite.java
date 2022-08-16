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

import io.undertow.Handlers;
import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.testutils.DefaultServer;
import io.undertow.testutils.HttpClientUtils;
import io.undertow.testutils.TestHttpClient;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.Deque;
import java.util.Map;

import static io.undertow.Handlers.path;
import static io.undertow.Handlers.rewrite;


/**
 * Tests the redirect handler
 *
 * @author Stuart Douglas
 */
@RunWith(DefaultServer.class)
public class SetAttributeTestCase {

    @Test
    public void testRewrite() throws IOException {
        DefaultServer.setRootHandler(
                rewrite("regex['/somePath/(.*)']", "/otherPath/$1", getClass().getClassLoader(), path()
                        .addPath("/otherPath", new InfoHandler())
                        .addPath("/relative",
                                rewrite("path-template['/foo/{bar}/{woz}']", "/foo?bar=${bar}&woz=${woz}", getClass().getClassLoader(), new InfoHandler()))
                ));

        TestHttpClient client = new TestHttpClient();
        try {
            HttpGet get = new HttpGet(DefaultServer.getDefaultServerURL() + "/relative/foo/a/b");
            HttpResponse result = client.execute(get);
            Assert.assertEquals(200, result.getStatusLine().getStatusCode());
            String response = HttpClientUtils.readResponse(result);
            Assert.assertEquals("URI: /relative/foo?bar=a&woz=b relative: /foo QS:?bar=a&woz=b bar: a woz: b", response);

            get = new HttpGet(DefaultServer.getDefaultServerURL() + "/somePath/foo/a/b");
            result = client.execute(get);
            Assert.assertEquals(200, result.getStatusLine().getStatusCode());
            response = HttpClientUtils.readResponse(result);
            Assert.assertEquals("URI: /otherPath/foo/a/b relative: /foo/a/b QS:", response);


            get = new HttpGet(DefaultServer.getDefaultServerURL() + "/somePath/foo?a=b");
            result = client.execute(get);
            Assert.assertEquals(200, result.getStatusLine().getStatusCode());
            response = HttpClientUtils.readResponse(result);
            Assert.assertEquals("URI: /otherPath/foo relative: /foo QS:a=b a: b", response);

        } finally {
            client.getConnectionManager().shutdown();
        }
    }

    private class InfoHandler implements HttpHandler {
        @Override
        public void handleRequest(HttpServerExchange exchange) throws Exception {
            final StringBuilder sb = new StringBuilder("URI: " + exchange.getRequestURI()
                    + " relative: " + exchange.getRelativePath()
                    + " QS:" + exchange.getQueryString());
            for (Map.Entry<String, Deque<String>> param : exchange.getQueryParameters().entrySet()) {
                sb.append(" " + param.getKey() + ": " + param.getValue().getFirst());
            }
            exchange.getResponseSender().send(sb.toString());
        }
    }
}
