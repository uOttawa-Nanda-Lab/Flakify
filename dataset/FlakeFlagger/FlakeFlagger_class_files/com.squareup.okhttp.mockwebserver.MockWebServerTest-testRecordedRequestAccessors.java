/*
 * Copyright (C) 2011 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.squareup.okhttp.mockwebserver;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import junit.framework.TestCase;

public final class MockWebServerTest extends TestCase {

    private MockWebServer server = new MockWebServer();

    public void testRecordedRequestAccessors(){List<String> headers=Arrays.asList("User-Agent: okhttp","Cookie: s=square","Cookie: a=android","X-Whitespace:  left","X-Whitespace:right  ","X-Whitespace:  both  ");List<Integer> chunkSizes=Collections.emptyList();byte[] body={'A','B','C'};String requestLine="GET / HTTP/1.1";RecordedRequest request=new RecordedRequest(requestLine,headers,chunkSizes,body.length,body,0,null);assertEquals("s=square",request.getHeader("cookie"));assertEquals(Arrays.asList("s=square","a=android"),request.getHeaders("cookie"));assertEquals("left",request.getHeader("x-whitespace"));assertEquals(Arrays.asList("left","right","both"),request.getHeaders("x-whitespace"));assertEquals("ABC",request.getUtf8Body());}
}
