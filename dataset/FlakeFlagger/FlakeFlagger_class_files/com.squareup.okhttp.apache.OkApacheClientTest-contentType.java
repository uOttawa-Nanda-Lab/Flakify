package com.squareup.okhttp.apache;

import com.squareup.okhttp.mockwebserver.MockResponse;
import com.squareup.okhttp.mockwebserver.MockWebServer;
import com.squareup.okhttp.mockwebserver.RecordedRequest;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.InputStreamEntity;
import org.apache.http.util.EntityUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class OkApacheClientTest {
  private MockWebServer server;
  private OkApacheClient client;

  @Before public void setUp() throws IOException {
    client = new OkApacheClient();
    server = new MockWebServer();
    server.play();
  }

  @Test public void contentType() throws Exception{server.enqueue(new MockResponse().setBody("<html><body><h1>Hello, World!</h1></body></html>").setHeader("Content-Type","text/html"));server.enqueue(new MockResponse().setBody("{\"Message\": { \"text\": \"Hello, World!\" } }").setHeader("Content-Type","application/json"));server.enqueue(new MockResponse().setBody("Hello, World!"));HttpGet request1=new HttpGet(server.getUrl("/").toURI());HttpResponse response1=client.execute(request1);Header[] headers1=response1.getHeaders("Content-Type");assertEquals(1,headers1.length);assertEquals("text/html",headers1[0].getValue());assertNotNull(response1.getEntity().getContentType());assertEquals("text/html",response1.getEntity().getContentType().getValue());HttpGet request2=new HttpGet(server.getUrl("/").toURI());HttpResponse response2=client.execute(request2);Header[] headers2=response2.getHeaders("Content-Type");assertEquals(1,headers2.length);assertEquals("application/json",headers2[0].getValue());assertNotNull(response2.getEntity().getContentType());assertEquals("application/json",response2.getEntity().getContentType().getValue());HttpGet request3=new HttpGet(server.getUrl("/").toURI());HttpResponse response3=client.execute(request3);Header[] headers3=response3.getHeaders("Content-Type");assertEquals(0,headers3.length);assertNull(response3.getEntity().getContentType());}
}
