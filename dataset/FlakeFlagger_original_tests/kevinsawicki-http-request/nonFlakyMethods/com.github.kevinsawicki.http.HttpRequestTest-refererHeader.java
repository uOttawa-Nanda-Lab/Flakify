/** 
 * Verify 'Referer' header
 * @throws Exception
 */
@Test public void refererHeader() throws Exception {
  final AtomicReference<String> referer=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      referer.set(request.getHeader("Referer"));
      response.setStatus(HTTP_OK);
    }
  }
;
  assertTrue(post(url).referer("http://heroku.com").ok());
  assertEquals("http://heroku.com",referer.get());
}
