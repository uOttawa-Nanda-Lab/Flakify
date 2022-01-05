/** 
 * Clients who adhere to <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec8.html#sec8.2.3">100 Status</a> expect the server to send an interim response with status code 100 before they send their payload. <h4>Note</h4> JRE 6 only passes this test if {@code -Dsun.net.http.allowRestrictedHeaders=true} is set.
 */
public void testExpect100ContinueWithBody() throws Exception {
  server.enqueue(new MockResponse());
  server.play();
  URL url=server.getUrl("/");
  HttpURLConnection connection=(HttpURLConnection)url.openConnection();
  connection.setRequestMethod("PUT");
  connection.setAllowUserInteraction(false);
  connection.setRequestProperty("Expect","100-continue");
  connection.setDoOutput(true);
  connection.getOutputStream().write("hello".getBytes());
  assertEquals(HttpURLConnection.HTTP_OK,connection.getResponseCode());
  assertEquals(server.getRequestCount(),1);
  RecordedRequest request=server.takeRequest();
  assertEquals(request.getRequestLine(),"PUT / HTTP/1.1");
  assertEquals("5",request.getHeader("Content-Length"));
  assertEquals(5,request.getBodySize());
  assertEquals("hello",new String(request.getBody()));
  assertEquals("100-continue",request.getHeader("Expect"));
}
