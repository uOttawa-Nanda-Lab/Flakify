@Test public void responseHeaders() throws IOException, InterruptedException {
  server.enqueue(new MockResponse().setStatus("HTTP/1.0 200 Fantastic").addHeader("A: c").addHeader("B: d").addHeader("A: e").setChunkedBody("ABCDE\nFGHIJ\nKLMNO\nPQR",8));
  server.play();
  connection=client.open(server.getUrl("/"));
  assertEquals(200,connection.getResponseCode());
  assertEquals("Fantastic",connection.getResponseMessage());
  assertEquals("HTTP/1.0 200 Fantastic",connection.getHeaderField(null));
  Map<String,List<String>> responseHeaders=connection.getHeaderFields();
  assertEquals(Arrays.asList("HTTP/1.0 200 Fantastic"),responseHeaders.get(null));
  assertEquals(newSet("c","e"),new HashSet<String>(responseHeaders.get("A")));
  assertEquals(newSet("c","e"),new HashSet<String>(responseHeaders.get("a")));
  try {
    responseHeaders.put("N",Arrays.asList("o"));
    fail("Modified an unmodifiable view.");
  }
 catch (  UnsupportedOperationException expected) {
  }
  try {
    responseHeaders.get("A").add("f");
    fail("Modified an unmodifiable view.");
  }
 catch (  UnsupportedOperationException expected) {
  }
  assertEquals("A",connection.getHeaderFieldKey(0));
  assertEquals("c",connection.getHeaderField(0));
  assertEquals("B",connection.getHeaderFieldKey(1));
  assertEquals("d",connection.getHeaderField(1));
  assertEquals("A",connection.getHeaderFieldKey(2));
  assertEquals("e",connection.getHeaderField(2));
}
