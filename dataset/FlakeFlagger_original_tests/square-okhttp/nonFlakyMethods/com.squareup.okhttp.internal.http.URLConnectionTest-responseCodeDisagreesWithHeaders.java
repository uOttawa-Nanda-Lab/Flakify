@Test public void responseCodeDisagreesWithHeaders() throws IOException, InterruptedException {
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_NO_CONTENT).setBody("This body is not allowed!"));
  server.play();
  URLConnection connection=client.open(server.getUrl("/"));
  assertEquals("This body is not allowed!",readAscii(connection.getInputStream(),Integer.MAX_VALUE));
}
