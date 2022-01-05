/** 
 * Make a GET request with basic proxy authentication specified
 * @throws Exception
 */
@Test public void basicProxyAuthentication() throws Exception {
  final AtomicBoolean finalHostReached=new AtomicBoolean(false);
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      finalHostReached.set(true);
      response.setStatus(HTTP_OK);
    }
  }
;
  assertTrue(get(url).useProxy("localhost",proxyPort).proxyBasic("user","p4ssw0rd").ok());
  assertEquals("user",proxyUser.get());
  assertEquals("p4ssw0rd",proxyPassword.get());
  assertEquals(true,finalHostReached.get());
  assertEquals(1,proxyHitCount.get());
}
