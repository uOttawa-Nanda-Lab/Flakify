/** 
 * Verify 'User-Agent' request header
 * @throws Exception
 */
@Test public void userAgentHeader() throws Exception {
  final AtomicReference<String> header=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      header.set(request.getHeader("User-Agent"));
    }
  }
;
  assertTrue(get(url).userAgent("browser 1.0").ok());
  assertEquals("browser 1.0",header.get());
}
