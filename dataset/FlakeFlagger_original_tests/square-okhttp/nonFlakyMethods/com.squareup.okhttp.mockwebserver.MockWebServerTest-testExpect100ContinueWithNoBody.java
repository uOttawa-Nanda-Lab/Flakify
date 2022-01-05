public void testExpect100ContinueWithNoBody() throws Exception {
  server.enqueue(new MockResponse());
  server.play();
  URL url=server.getUrl("/");
  HttpURLConnection connection=(HttpURLConnection)url.openConnection();
  connection.setRequestMethod("PUT");
  connection.setAllowUserInteraction(false);
  connection.setRequestProperty("Expect","100-continue");
  connection.setRequestProperty("Content-Length","0");
  connection.setDoOutput(true);
  connection.setFixedLengthStreamingMode(0);
  assertEquals(HttpURLConnection.HTTP_OK,connection.getResponseCode());
  assertEquals(server.getRequestCount(),1);
  RecordedRequest request=server.takeRequest();
  assertEquals(request.getRequestLine(),"PUT / HTTP/1.1");
  assertEquals("0",request.getHeader("Content-Length"));
  assertEquals(0,request.getBodySize());
  assertEquals("100-continue",request.getHeader("Expect"));
}
