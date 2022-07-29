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

  @Test public void contentEncoding() throws Exception{String text="{\"Message\": { \"text\": \"Hello, World!\" } }";ByteArrayOutputStream bodyBytes=new ByteArrayOutputStream();OutputStreamWriter body=new OutputStreamWriter(new GZIPOutputStream(bodyBytes),Charset.forName("UTF-8"));body.write(text);body.close();server.enqueue(new MockResponse().setBody(bodyBytes.toByteArray()).setHeader("Content-Encoding","gzip"));byte[] tmp=new byte[32];HttpGet request1=new HttpGet(server.getUrl("/").toURI());request1.setHeader("Accept-encoding","gzip");HttpResponse response1=client.execute(request1);Header[] headers1=response1.getHeaders("Content-Encoding");assertEquals(1,headers1.length);assertEquals("gzip",headers1[0].getValue());assertNotNull(response1.getEntity().getContentEncoding());assertEquals("gzip",response1.getEntity().getContentEncoding().getValue());InputStream content=new GZIPInputStream(response1.getEntity().getContent());ByteArrayOutputStream rspBodyBytes=new ByteArrayOutputStream();for (int len=content.read(tmp);len >= 0;len=content.read(tmp)){rspBodyBytes.write(tmp,0,len);}String decodedContent=rspBodyBytes.toString("UTF-8");assertEquals(text,decodedContent);}
}
