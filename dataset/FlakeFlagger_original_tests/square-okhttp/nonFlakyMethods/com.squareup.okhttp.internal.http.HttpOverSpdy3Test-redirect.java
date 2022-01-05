@Test public void redirect() throws Exception {
  server.enqueue(new MockResponse().setResponseCode(HttpURLConnection.HTTP_MOVED_TEMP).addHeader("Location: /foo").setBody("This page has moved!"));
  server.enqueue(new MockResponse().setBody("This is the new location!"));
  server.play();
  connection=client.open(server.getUrl("/"));
  assertContent("This is the new location!",connection,Integer.MAX_VALUE);
  RecordedRequest request1=server.takeRequest();
  assertEquals("/",request1.getPath());
  RecordedRequest request2=server.takeRequest();
  assertEquals("/foo",request2.getPath());
}
