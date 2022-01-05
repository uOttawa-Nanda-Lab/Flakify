@Test public void clientConfiguredGzipContentEncoding() throws Exception {
  byte[] bodyBytes=gzip("ABCDEFGHIJKLMNOPQRSTUVWXYZ".getBytes("UTF-8"));
  server.enqueue(new MockResponse().setBody(bodyBytes).addHeader("Content-Encoding: gzip"));
  server.play();
  URLConnection connection=client.open(server.getUrl("/"));
  connection.addRequestProperty("Accept-Encoding","gzip");
  InputStream gunzippedIn=new GZIPInputStream(connection.getInputStream());
  assertEquals("ABCDEFGHIJKLMNOPQRSTUVWXYZ",readAscii(gunzippedIn,Integer.MAX_VALUE));
  assertEquals(bodyBytes.length,connection.getContentLength());
  RecordedRequest request=server.takeRequest();
  assertContains(request.getHeaders(),"Accept-Encoding: gzip");
}
