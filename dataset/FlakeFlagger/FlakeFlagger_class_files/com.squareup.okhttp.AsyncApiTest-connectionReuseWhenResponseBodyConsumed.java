/*
 * Copyright (C) 2013 Square, Inc.
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
package com.squareup.okhttp;

import com.squareup.okhttp.internal.RecordingHostnameVerifier;
import com.squareup.okhttp.internal.SslContextBuilder;
import com.squareup.okhttp.mockwebserver.Dispatcher;
import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import com.squareup.okhttp.mockwebserver.SocketPolicy;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;
import javax.net.ssl.SSLContext;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public final class AsyncApiTest {
  private MockWebServer server = new MockWebServer();
  private OkHttpClient client = new OkHttpClient();
  private RecordingReceiver receiver = new RecordingReceiver();

  private static final SSLContext sslContext = SslContextBuilder.localhost();
  private HttpResponseCache cache;

  @Before public void setUp() throws Exception {
    String tmp = System.getProperty("java.io.tmpdir");
    File cacheDir = new File(tmp, "HttpCache-" + UUID.randomUUID());
    cache = new HttpResponseCache(cacheDir, Integer.MAX_VALUE);
  }

  @Test public void connectionReuseWhenResponseBodyConsumed() throws Exception{server.enqueue(new MockResponse().setBody("abc"));server.enqueue(new MockResponse().setBody("def"));server.play();Request request=new Request.Builder().url(server.getUrl("/a")).build();client.enqueue(request,new Response.Receiver(){@Override public void onFailure(Failure failure){throw new AssertionError();}@Override public boolean onResponse(Response response) throws IOException{InputStream bytes=response.body().byteStream();assertEquals('a',bytes.read());assertEquals('b',bytes.read());assertEquals('c',bytes.read());client.enqueue(new Request.Builder().url(server.getUrl("/b")).build(),receiver);return true;}});receiver.await(server.getUrl("/b")).assertCode(200).assertBody("def");assertEquals(0,server.takeRequest().getSequenceNumber());assertEquals(1,server.takeRequest().getSequenceNumber());}
}
