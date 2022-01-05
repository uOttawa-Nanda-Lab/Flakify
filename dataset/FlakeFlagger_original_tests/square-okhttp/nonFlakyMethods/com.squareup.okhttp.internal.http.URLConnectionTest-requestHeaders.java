@Test public void requestHeaders() throws IOException, InterruptedException {
  server.enqueue(new MockResponse());
  server.play();
  connection=client.open(server.getUrl("/"));
  connection.addRequestProperty("D","e");
  connection.addRequestProperty("D","f");
  assertEquals("f",connection.getRequestProperty("D"));
  assertEquals("f",connection.getRequestProperty("d"));
  Map<String,List<String>> requestHeaders=connection.getRequestProperties();
  assertEquals(newSet("e","f"),new HashSet<String>(requestHeaders.get("D")));
  assertEquals(newSet("e","f"),new HashSet<String>(requestHeaders.get("d")));
  try {
    requestHeaders.put("G",Arrays.asList("h"));
    fail("Modified an unmodifiable view.");
  }
 catch (  UnsupportedOperationException expected) {
  }
  try {
    requestHeaders.get("D").add("i");
    fail("Modified an unmodifiable view.");
  }
 catch (  UnsupportedOperationException expected) {
  }
  try {
    connection.setRequestProperty(null,"j");
    fail();
  }
 catch (  NullPointerException expected) {
  }
  try {
    connection.addRequestProperty(null,"k");
    fail();
  }
 catch (  NullPointerException expected) {
  }
  connection.setRequestProperty("NullValue",null);
  assertNull(connection.getRequestProperty("NullValue"));
  connection.addRequestProperty("AnotherNullValue",null);
  assertNull(connection.getRequestProperty("AnotherNullValue"));
  connection.getResponseCode();
  RecordedRequest request=server.takeRequest();
  assertContains(request.getHeaders(),"D: e");
  assertContains(request.getHeaders(),"D: f");
  assertContainsNoneMatching(request.getHeaders(),"NullValue.*");
  assertContainsNoneMatching(request.getHeaders(),"AnotherNullValue.*");
  assertContainsNoneMatching(request.getHeaders(),"G:.*");
  assertContainsNoneMatching(request.getHeaders(),"null:.*");
  try {
    connection.addRequestProperty("N","o");
    fail("Set header after connect");
  }
 catch (  IllegalStateException expected) {
  }
  try {
    connection.setRequestProperty("P","q");
    fail("Set header after connect");
  }
 catch (  IllegalStateException expected) {
  }
  try {
    connection.getRequestProperties();
    fail();
  }
 catch (  IllegalStateException expected) {
  }
}
