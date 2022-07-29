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

    /**
	 * Clients who adhere to <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec8.html#sec8.2.3">100 Status</a> expect the server to send an interim response with status code 100 before they send their payload. <h4>Note</h4> JRE 6 only passes this test if  {@code  -Dsun.net.http.allowRestrictedHeaders=true}  is set.
	 */
	public void testExpect100ContinueWithBody() throws Exception {
		server.enqueue(new MockResponse());
		server.play();
		URL url = server.getUrl("/");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("PUT");
		connection.setAllowUserInteraction(false);
		connection.setRequestProperty("Expect", "100-continue");
		connection.setDoOutput(true);
		connection.getOutputStream().write("hello".getBytes());
		assertEquals(HttpURLConnection.HTTP_OK, connection.getResponseCode());
		assertEquals(server.getRequestCount(), 1);
		RecordedRequest request = server.takeRequest();
		assertEquals(request.getRequestLine(), "PUT / HTTP/1.1");
		assertEquals("5", request.getHeader("Content-Length"));
		assertEquals(5, request.getBodySize());
		assertEquals("hello", new String(request.getBody()));
		assertEquals("100-continue", request.getHeader("Expect"));
	}
}
