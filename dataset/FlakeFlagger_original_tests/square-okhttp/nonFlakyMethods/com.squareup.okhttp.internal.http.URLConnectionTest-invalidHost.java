@Test public void invalidHost() throws Exception {
  URL url=new URL("http://1234.1.1.1/index.html");
  HttpURLConnection connection=client.open(url);
  try {
    connection.connect();
    fail();
  }
 catch (  UnknownHostException expected) {
  }
}
