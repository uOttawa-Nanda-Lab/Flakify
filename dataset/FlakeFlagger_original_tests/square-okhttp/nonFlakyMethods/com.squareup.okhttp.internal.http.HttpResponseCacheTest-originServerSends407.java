/** 
 * Response code 407 should only come from proxy servers. Android's client throws if it is sent by an origin server.
 */
@Test public void originServerSends407() throws Exception {
  server.enqueue(new MockResponse().setResponseCode(407));
  server.play();
  URL url=server.getUrl("/");
  HttpURLConnection conn=openConnection(url);
  try {
    conn.getResponseCode();
    fail();
  }
 catch (  IOException expected) {
  }
}
