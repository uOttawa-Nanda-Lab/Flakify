@Test public void authorizationRequestHeaderPreventsCaching() throws Exception {
  server.enqueue(new MockResponse().addHeader("Last-Modified: " + formatDate(-2,TimeUnit.MINUTES)).addHeader("Cache-Control: max-age=60").setBody("A"));
  server.enqueue(new MockResponse().setBody("B"));
  server.play();
  URL url=server.getUrl("/");
  URLConnection connection=openConnection(url);
  connection.addRequestProperty("Authorization","password");
  assertEquals("A",readAscii(connection));
  assertEquals("B",readAscii(openConnection(url)));
}
