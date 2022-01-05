@Test public void connectionCloseInResponse() throws IOException, InterruptedException {
  server.enqueue(new MockResponse().addHeader("Connection: close"));
  server.enqueue(new MockResponse());
  server.play();
  HttpURLConnection a=client.open(server.getUrl("/"));
  assertEquals(200,a.getResponseCode());
  HttpURLConnection b=client.open(server.getUrl("/"));
  assertEquals(200,b.getResponseCode());
  assertEquals(0,server.takeRequest().getSequenceNumber());
  assertEquals("When connection: close is used, each request should get its own connection",0,server.takeRequest().getSequenceNumber());
}
