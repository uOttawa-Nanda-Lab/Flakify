/** 
 * Get a 500
 * @throws Exception
 */
@Test public void serverErrorCode() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_INTERNAL_ERROR);
    }
  }
;
  HttpRequest request=get(url);
  assertNotNull(request);
  assertTrue(request.serverError());
}
