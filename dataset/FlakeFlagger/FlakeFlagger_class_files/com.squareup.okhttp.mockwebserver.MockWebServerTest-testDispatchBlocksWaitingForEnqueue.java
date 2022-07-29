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
	 * Test that MockWebServer blocks for a call to enqueue() if a request is made before a mock response is ready.
	 */public void testDispatchBlocksWaitingForEnqueue() throws Exception{server.play();new Thread(){@Override public void run(){try {Thread.sleep(1000);} catch (InterruptedException ignored){}server.enqueue(new MockResponse().setBody("enqueued in the background"));}}.start();URLConnection connection=server.getUrl("/").openConnection();InputStream in=connection.getInputStream();BufferedReader reader=new BufferedReader(new InputStreamReader(in));assertEquals("enqueued in the background",reader.readLine());}
}
