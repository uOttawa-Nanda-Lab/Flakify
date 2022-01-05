@Test public void serverReturnsDocumentOlderThanCache() throws Exception {
  server.enqueue(new MockResponse().setBody("A").addHeader("Last-Modified: " + formatDate(-2,TimeUnit.HOURS)).addHeader("Expires: " + formatDate(-1,TimeUnit.HOURS)));
  server.enqueue(new MockResponse().setBody("B").addHeader("Last-Modified: " + formatDate(-4,TimeUnit.HOURS)));
  server.play();
  URL url=server.getUrl("/");
  assertEquals("A",readAscii(openConnection(url)));
  assertEquals("A",readAscii(openConnection(url)));
}
