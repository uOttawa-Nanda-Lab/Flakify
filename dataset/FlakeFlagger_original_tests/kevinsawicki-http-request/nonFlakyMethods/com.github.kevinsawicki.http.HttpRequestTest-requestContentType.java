/** 
 * Verify 'Content-Type' header
 * @throws Exception
 */
@Test public void requestContentType() throws Exception {
  final AtomicReference<String> contentType=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      contentType.set(request.getContentType());
      response.setStatus(HTTP_OK);
    }
  }
;
  assertTrue(post(url).contentType("text/html","UTF-8").ok());
  assertEquals("text/html; charset=UTF-8",contentType.get());
}
