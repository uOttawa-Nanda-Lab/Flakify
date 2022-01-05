/** 
 * We've had a bug where we forget the HTTP response when we see response code 401. This causes a new HTTP request to be issued for every call into the URLConnection.
 */
@Test public void unauthorizedResponseHandling() throws IOException {
  MockResponse response=new MockResponse().addHeader("WWW-Authenticate: challenge").setResponseCode(401).setBody("Unauthorized");
  server.enqueue(response);
  server.enqueue(response);
  server.enqueue(response);
  server.play();
  URL url=server.getUrl("/");
  HttpURLConnection conn=client.open(url);
  assertEquals(401,conn.getResponseCode());
  assertEquals(401,conn.getResponseCode());
  assertEquals(401,conn.getResponseCode());
  assertEquals(1,server.getRequestCount());
}
