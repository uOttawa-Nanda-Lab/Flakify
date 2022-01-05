@Test public void connectionCloseWithRedirect() throws IOException, InterruptedException {
  MockResponse response=new MockResponse().setResponseCode(HttpURLConnection.HTTP_MOVED_TEMP).addHeader("Location: /foo").addHeader("Connection: close");
  server.enqueue(response);
  server.enqueue(new MockResponse().setBody("This is the new location!"));
  server.play();
  URLConnection connection=client.open(server.getUrl("/"));
  assertEquals("This is the new location!",readAscii(connection.getInputStream(),Integer.MAX_VALUE));
  assertEquals(0,server.takeRequest().getSequenceNumber());
  assertEquals("When connection: close is used, each request should get its own connection",0,server.takeRequest().getSequenceNumber());
}
