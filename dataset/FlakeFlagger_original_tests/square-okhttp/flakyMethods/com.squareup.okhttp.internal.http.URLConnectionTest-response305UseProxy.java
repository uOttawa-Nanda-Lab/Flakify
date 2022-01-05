@Test public void response305UseProxy() throws Exception {
  server.play();
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_USE_PROXY).addHeader("Location: " + server.getUrl("/")).setBody("This page has moved!"));
  server.enqueue(new MockResponse().setBody("Proxy Response"));
  connection=client.open(server.getUrl("/foo"));
  assertEquals("This page has moved!",readAscii(connection.getInputStream(),Integer.MAX_VALUE));
  RecordedRequest page1=server.takeRequest();
  assertEquals("GET /foo HTTP/1.1",page1.getRequestLine());
  assertEquals(1,server.getRequestCount());
}
