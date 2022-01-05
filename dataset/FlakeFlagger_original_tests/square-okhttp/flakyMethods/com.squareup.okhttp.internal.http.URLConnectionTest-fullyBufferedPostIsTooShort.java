@Test public void fullyBufferedPostIsTooShort() throws Exception {
  server.enqueue(new MockResponse().setBody("A"));
  server.play();
  connection=client.open(server.getUrl("/b"));
  connection.setRequestProperty("Content-Length","4");
  connection.setRequestMethod("POST");
  OutputStream out=connection.getOutputStream();
  out.write('a');
  out.write('b');
  out.write('c');
  try {
    out.close();
    fail();
  }
 catch (  IOException expected) {
  }
}
