/** 
 * Get a 304
 * @throws Exception
 */
@Test public void notModifiedCode() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_NOT_MODIFIED);
    }
  }
;
  HttpRequest request=get(url);
  assertNotNull(request);
  assertTrue(request.notModified());
}
