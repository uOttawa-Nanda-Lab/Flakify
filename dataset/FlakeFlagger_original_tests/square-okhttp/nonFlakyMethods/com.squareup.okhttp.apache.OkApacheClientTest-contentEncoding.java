@Test public void contentEncoding() throws Exception {
  String text="{\"Message\": { \"text\": \"Hello, World!\" } }";
  ByteArrayOutputStream bodyBytes=new ByteArrayOutputStream();
  OutputStreamWriter body=new OutputStreamWriter(new GZIPOutputStream(bodyBytes),Charset.forName("UTF-8"));
  body.write(text);
  body.close();
  server.enqueue(new MockResponse().setBody(bodyBytes.toByteArray()).setHeader("Content-Encoding","gzip"));
  byte[] tmp=new byte[32];
  HttpGet request1=new HttpGet(server.getUrl("/").toURI());
  request1.setHeader("Accept-encoding","gzip");
  HttpResponse response1=client.execute(request1);
  Header[] headers1=response1.getHeaders("Content-Encoding");
  assertEquals(1,headers1.length);
  assertEquals("gzip",headers1[0].getValue());
  assertNotNull(response1.getEntity().getContentEncoding());
  assertEquals("gzip",response1.getEntity().getContentEncoding().getValue());
  InputStream content=new GZIPInputStream(response1.getEntity().getContent());
  ByteArrayOutputStream rspBodyBytes=new ByteArrayOutputStream();
  for (int len=content.read(tmp); len >= 0; len=content.read(tmp)) {
    rspBodyBytes.write(tmp,0,len);
  }
  String decodedContent=rspBodyBytes.toString("UTF-8");
  assertEquals(text,decodedContent);
}
