/** 
 * Verify 'Content-Type' header
 * @throws Exception
 */
@Test public void requestContentTypeNullCharset() throws Exception {
  final AtomicReference<String> contentType=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      contentType.set(request.getContentType());
      response.setStatus(HTTP_OK);
    }
  }
;
  assertTrue(post(url).contentType("text/html",null).ok());
  assertEquals("text/html",contentType.get());
}
