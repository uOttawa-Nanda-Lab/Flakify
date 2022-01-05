@Test public void responseSourceHeaderFetched() throws IOException {
  server.enqueue(new MockResponse().setBody("A"));
  server.play();
  URLConnection connection=openConnection(server.getUrl("/"));
  assertEquals("A",readAscii(connection));
  String source=connection.getHeaderField(OkHeaders.RESPONSE_SOURCE);
  assertEquals(ResponseSource.NETWORK + " 200",source);
}
