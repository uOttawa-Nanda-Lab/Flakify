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

package io.undertow.server.handlers.path;

import java.io.IOException;
import java.util.Collections;
import java.util.Deque;
import java.util.Map;

import io.undertow.server.HttpHandler;
import io.undertow.server.HttpServerExchange;
import io.undertow.server.handlers.PathHandler;
import io.undertow.testutils.DefaultServer;
import io.undertow.testutils.HttpClientUtils;
import io.undertow.util.HttpString;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import io.undertow.testutils.TestHttpClient;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Tests that the path handler works as expected
 *
 * @author Stuart Douglas
 */
@RunWith(DefaultServer.class)
public class PathTestCase {

    public static final String MATCHED = "matched";
    public static final String PATH = "path";

    @Test public void testBasicPathHanding() throws IOException{TestHttpClient client=new TestHttpClient();try {final PathHandler handler=new PathHandler();handler.addPath("a",new RemainingPathHandler("/a"));handler.addPath("/aa",new RemainingPathHandler("/aa"));handler.addPath("/aa/anotherSubPath",new RemainingPathHandler("/aa/anotherSubPath"));final PathHandler sub=new PathHandler();handler.addPath("/path",sub);sub.addPath("/subpath",new RemainingPathHandler("/subpath"));sub.addPath("/",new RemainingPathHandler("/path"));DefaultServer.setRootHandler(handler);HttpGet get=new HttpGet(DefaultServer.getDefaultServerURL() + "/notamatchingpath");HttpResponse result=client.execute(get);Assert.assertEquals(404,result.getStatusLine().getStatusCode());HttpClientUtils.readResponse(result);get=new HttpGet(DefaultServer.getDefaultServerURL() + "/");result=client.execute(get);Assert.assertEquals(404,result.getStatusLine().getStatusCode());HttpClientUtils.readResponse(result);runPathTest(client,"/path","/path","");runPathTest(client,"/path/a","/path","/a");runPathTest(client,"/path/subpath","/subpath","");runPathTest(client,"/path/subpath/","/subpath","/");runPathTest(client,"/path/subpath/foo","/subpath","/foo");runPathTest(client,"/a","/a","");runPathTest(client,"/aa/anotherSubPath","/aa/anotherSubPath","");runPathTest(client,"/aa/anotherSubPath/bob","/aa/anotherSubPath","/bob");runPathTest(client,"/aa?a=b","/aa","",Collections.singletonMap("a","b"));}  finally {client.getConnectionManager().shutdown();}}

    private void runPathTest(TestHttpClient client, String path, String expectedMatch, String expectedRemaining) throws IOException {
        runPathTest(client, path, expectedMatch, expectedRemaining, Collections.<String, String>emptyMap());
    }
    private void runPathTest(TestHttpClient client, String path, String expectedMatch, String expectedRemaining, Map<String, String> queryParams) throws IOException {
        HttpResponse result;HttpGet get = new HttpGet(DefaultServer.getDefaultServerURL() + path);
        result = client.execute(get);
        Assert.assertEquals(200, result.getStatusLine().getStatusCode());
        Header[] header = result.getHeaders(MATCHED);
        Assert.assertEquals(expectedMatch, header[0].getValue());
        header = result.getHeaders(PATH);
        Assert.assertEquals(expectedRemaining, header[0].getValue());
        HttpClientUtils.readResponse(result);
        for(Map.Entry<String, String> entry : queryParams.entrySet()) {
            header = result.getHeaders(entry.getKey());
            Assert.assertEquals(entry.getValue(), header[0].getValue());
        }
    }

    private static class RemainingPathHandler implements HttpHandler {

        private final String matched;

        private RemainingPathHandler(String matched) {
            this.matched = matched;
        }

        @Override
        public void handleRequest(HttpServerExchange exchange) throws Exception {
            exchange.getResponseHeaders().add(new HttpString(MATCHED), matched);
            exchange.getResponseHeaders().add(new HttpString(PATH), exchange.getRelativePath());
            for(Map.Entry<String, Deque<String>> param : exchange.getQueryParameters().entrySet()) {
                exchange.getResponseHeaders().put(new HttpString(param.getKey()), param.getValue().getFirst());
            }
            exchange.endExchange();
        }
    }

}
