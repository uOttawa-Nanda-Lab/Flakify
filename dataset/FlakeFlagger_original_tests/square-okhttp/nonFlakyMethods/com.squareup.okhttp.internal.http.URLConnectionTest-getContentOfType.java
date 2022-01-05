@Test public void getContentOfType() throws Exception {
  server.enqueue(new MockResponse().addHeader("Content-Type: text/plain").setBody("A"));
  server.play();
  connection=client.open(server.getUrl("/"));
  try {
    connection.getContent(null);
    fail();
  }
 catch (  NullPointerException expected) {
  }
  try {
    connection.getContent(new Class[]{null});
    fail();
  }
 catch (  NullPointerException expected) {
  }
  assertNull(connection.getContent(new Class[]{getClass()}));
}
