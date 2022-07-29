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

  @Test public void jsonTransparentGzipResponse() throws Exception{String text="{\"Message\": { \"text\": \"Hello, World!\" } }";ByteArrayOutputStream bodyBytes=new ByteArrayOutputStream();OutputStreamWriter body=new OutputStreamWriter(new GZIPOutputStream(bodyBytes),Charset.forName("UTF-8"));body.write(text);body.close();server.enqueue(new MockResponse().setBody(bodyBytes.toByteArray()).setHeader("Content-Encoding","gzip").setHeader("Content-Type","application/json"));byte[] tmp=new byte[32];HttpGet request1=new HttpGet(server.getUrl("/").toURI());HttpResponse response1=client.execute(request1);Header[] headers1a=response1.getHeaders("Content-Encoding");assertEquals(0,headers1a.length);assertNull(response1.getEntity().getContentEncoding());Header[] headers1b=response1.getHeaders("Content-Length");assertEquals(0,headers1b.length);assertTrue(response1.getEntity().getContentLength() < 0);Header[] headers1c=response1.getHeaders("Content-Type");assertEquals(1,headers1c.length);assertEquals("application/json",headers1c[0].getValue());assertNotNull(response1.getEntity().getContentType());assertEquals("application/json",response1.getEntity().getContentType().getValue());InputStream content=response1.getEntity().getContent();ByteArrayOutputStream rspBodyBytes=new ByteArrayOutputStream();for (int len=content.read(tmp);len >= 0;len=content.read(tmp)){rspBodyBytes.write(tmp,0,len);}String decodedContent=rspBodyBytes.toString("UTF-8");assertEquals(text,decodedContent);}
}
