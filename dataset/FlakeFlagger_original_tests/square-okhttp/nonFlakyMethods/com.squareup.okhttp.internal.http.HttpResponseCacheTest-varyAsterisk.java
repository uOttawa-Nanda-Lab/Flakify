@Test public void varyAsterisk() throws Exception {
  server.enqueue(new MockResponse().addHeader("Cache-Control: max-age=60").addHeader("Vary: *").setBody("A"));
  server.enqueue(new MockResponse().setBody("B"));
  server.play();
  assertEquals("A",readAscii(openConnection(server.getUrl("/"))));
  assertEquals("B",readAscii(openConnection(server.getUrl("/"))));
}
