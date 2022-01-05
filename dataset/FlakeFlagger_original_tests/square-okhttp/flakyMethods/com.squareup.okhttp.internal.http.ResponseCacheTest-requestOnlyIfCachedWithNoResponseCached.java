@Test public void requestOnlyIfCachedWithNoResponseCached() throws IOException {
  server.play();
  HttpURLConnection connection=openConnection(server.getUrl("/"));
  connection.addRequestProperty("Cache-Control","only-if-cached");
  assertGatewayTimeout(connection);
}
