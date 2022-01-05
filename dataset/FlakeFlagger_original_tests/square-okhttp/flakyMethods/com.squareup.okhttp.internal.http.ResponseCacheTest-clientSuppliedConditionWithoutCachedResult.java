@Test public void clientSuppliedConditionWithoutCachedResult() throws Exception {
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_NOT_MODIFIED));
  server.play();
  HttpURLConnection connection=openConnection(server.getUrl("/"));
  String clientIfModifiedSince=formatDate(-24,TimeUnit.HOURS);
  connection.addRequestProperty("If-Modified-Since",clientIfModifiedSince);
  assertEquals(HttpURLConnection.HTTP_NOT_MODIFIED,connection.getResponseCode());
  assertEquals("",readAscii(connection));
}
