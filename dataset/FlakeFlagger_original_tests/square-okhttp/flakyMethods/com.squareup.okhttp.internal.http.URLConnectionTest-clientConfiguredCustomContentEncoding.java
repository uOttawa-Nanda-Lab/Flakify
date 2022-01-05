@Test public void clientConfiguredCustomContentEncoding() throws Exception {
  server.enqueue(new MockResponse().setBody("ABCDE").addHeader("Content-Encoding: custom"));
  server.play();
  URLConnection connection=client.open(server.getUrl("/"));
  connection.addRequestProperty("Accept-Encoding","custom");
  assertEquals("ABCDE",readAscii(connection.getInputStream(),Integer.MAX_VALUE));
  RecordedRequest request=server.takeRequest();
  assertContains(request.getHeaders(),"Accept-Encoding: custom");
}
