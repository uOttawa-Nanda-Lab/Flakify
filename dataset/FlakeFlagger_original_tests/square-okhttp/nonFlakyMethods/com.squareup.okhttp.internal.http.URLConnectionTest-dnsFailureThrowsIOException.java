@Test public void dnsFailureThrowsIOException() throws IOException {
  connection=client.open(new URL("http://host.unlikelytld"));
  try {
    connection.connect();
    fail();
  }
 catch (  IOException expected) {
  }
}
