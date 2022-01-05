@Test public void jsonGzipResponse() throws Exception {
  String text="{\"Message\": { \"text\": \"Hello, World!\" } }";
  ByteArrayOutputStream bodyBytes=new ByteArrayOutputStream();
  OutputStreamWriter body=new OutputStreamWriter(new GZIPOutputStream(bodyBytes),Charset.forName("UTF-8"));
  body.write(text);
  body.close();
  server.enqueue(new MockResponse().setBody(bodyBytes.toByteArray()).setHeader("Content-Encoding","gzip").setHeader("Content-Type","application/json"));
  byte[] tmp=new byte[32];
  HttpGet request1=new HttpGet(server.getUrl("/").toURI());
  request1.setHeader("Accept-encoding","gzip");
  HttpResponse response1=client.execute(request1);
  Header[] headers1a=response1.getHeaders("Content-Encoding");
  assertEquals(1,headers1a.length);
  assertEquals("gzip",headers1a[0].getValue());
  assertNotNull(response1.getEntity().getContentEncoding());
  assertEquals("gzip",response1.getEntity().getContentEncoding().getValue());
  Header[] headers1b=response1.getHeaders("Content-Type");
  assertEquals(1,headers1b.length);
  assertEquals("application/json",headers1b[0].getValue());
  assertNotNull(response1.getEntity().getContentType());
  assertEquals("application/json",response1.getEntity().getContentType().getValue());
  InputStream content=new GZIPInputStream(response1.getEntity().getContent());
  ByteArrayOutputStream rspBodyBytes=new ByteArrayOutputStream();
  for (int len=content.read(tmp); len >= 0; len=content.read(tmp)) {
    rspBodyBytes.write(tmp,0,len);
  }
  String decodedContent=rspBodyBytes.toString("UTF-8");
  assertEquals(text,decodedContent);
}
