@Test public void defaultExpirationDateFullyCachedForLessThan24Hours() throws Exception {
  server.enqueue(new MockResponse().addHeader("Last-Modified: " + formatDate(-105,TimeUnit.SECONDS)).addHeader("Date: " + formatDate(-5,TimeUnit.SECONDS)).setBody("A"));
  server.play();
  URL url=server.getUrl("/");
  assertEquals("A",readAscii(openConnection(url)));
  URLConnection connection=openConnection(url);
  assertEquals("A",readAscii(connection));
  assertNull(connection.getHeaderField("Warning"));
}
