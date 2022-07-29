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

    public void testResponseTimeout() throws Exception {
		server.enqueue(new MockResponse().setBody("ABC").clearHeaders().addHeader("Content-Length: 4"));
		server.enqueue(new MockResponse().setBody("DEF"));
		server.play();
		URLConnection urlConnection = server.getUrl("/").openConnection();
		urlConnection.setReadTimeout(1000);
		InputStream in = urlConnection.getInputStream();
		assertEquals('A', in.read());
		assertEquals('B', in.read());
		assertEquals('C', in.read());
		try {
			in.read();
			fail();
		} catch (SocketTimeoutException expected) {
		}
		URLConnection urlConnection2 = server.getUrl("/").openConnection();
		InputStream in2 = urlConnection2.getInputStream();
		assertEquals('D', in2.read());
		assertEquals('E', in2.read());
		assertEquals('F', in2.read());
		assertEquals(-1, in2.read());
		assertEquals(0, server.takeRequest().getSequenceNumber());
		assertEquals(0, server.takeRequest().getSequenceNumber());
	}
}
