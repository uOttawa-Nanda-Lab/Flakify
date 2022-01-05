@Test public void fullyBufferedPostIsTooLong() throws Exception {
  server.enqueue(new MockResponse().setBody("A"));
  server.play();
  connection=client.open(server.getUrl("/b"));
  connection.setRequestProperty("Content-Length","3");
  connection.setRequestMethod("POST");
  OutputStream out=connection.getOutputStream();
  out.write('a');
  out.write('b');
  out.write('c');
  try {
    out.write('d');
    out.flush();
    fail();
  }
 catch (  IOException expected) {
  }
}
