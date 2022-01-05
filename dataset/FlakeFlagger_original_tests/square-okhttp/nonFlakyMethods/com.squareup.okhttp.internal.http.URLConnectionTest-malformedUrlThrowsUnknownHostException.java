@Test public void malformedUrlThrowsUnknownHostException() throws IOException {
  connection=client.open(new URL("http:///foo.html"));
  try {
    connection.connect();
    fail();
  }
 catch (  UnknownHostException expected) {
  }
}
