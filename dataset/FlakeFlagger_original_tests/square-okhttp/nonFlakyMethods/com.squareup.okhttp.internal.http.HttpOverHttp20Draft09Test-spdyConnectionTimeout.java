@Test public void spdyConnectionTimeout() throws Exception {
  MockResponse response=new MockResponse().setBody("A");
  response.setBodyDelayTimeMs(1000);
  server.enqueue(response);
  server.play();
  HttpURLConnection connection1=client.open(server.getUrl("/"));
  connection1.setReadTimeout(2000);
  HttpURLConnection connection2=client.open(server.getUrl("/"));
  connection2.setReadTimeout(200);
  connection1.connect();
  connection2.connect();
  assertContent("A",connection1,Integer.MAX_VALUE);
}
