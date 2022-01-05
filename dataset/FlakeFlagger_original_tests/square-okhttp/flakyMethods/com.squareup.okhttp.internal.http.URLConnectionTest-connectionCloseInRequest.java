@Test public void connectionCloseInRequest() throws IOException, InterruptedException {
  server.enqueue(new MockResponse());
  server.enqueue(new MockResponse());
  server.play();
  HttpURLConnection a=client.open(server.getUrl("/"));
  a.setRequestProperty("Connection","close");
  assertEquals(200,a.getResponseCode());
  HttpURLConnection b=client.open(server.getUrl("/"));
  assertEquals(200,b.getResponseCode());
  assertEquals(0,server.takeRequest().getSequenceNumber());
  assertEquals("When connection: close is used, each request should get its own connection",0,server.takeRequest().getSequenceNumber());
}
