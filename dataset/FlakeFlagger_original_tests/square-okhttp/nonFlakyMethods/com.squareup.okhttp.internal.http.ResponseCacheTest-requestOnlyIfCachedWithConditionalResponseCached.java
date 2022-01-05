@Test public void requestOnlyIfCachedWithConditionalResponseCached() throws IOException {
  server.enqueue(new MockResponse().setBody("A").addHeader("Cache-Control: max-age=30").addHeader("Date: " + formatDate(-1,TimeUnit.MINUTES)));
  server.play();
  assertEquals("A",readAscii(openConnection(server.getUrl("/"))));
  HttpURLConnection connection=openConnection(server.getUrl("/"));
  connection.addRequestProperty("Cache-Control","only-if-cached");
  assertGatewayTimeout(connection);
}
