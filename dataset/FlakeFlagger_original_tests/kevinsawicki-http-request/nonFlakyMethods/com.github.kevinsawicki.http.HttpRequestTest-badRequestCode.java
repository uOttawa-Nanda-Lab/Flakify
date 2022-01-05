/** 
 * Get a 400
 * @throws Exception
 */
@Test public void badRequestCode() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_BAD_REQUEST);
    }
  }
;
  HttpRequest request=get(url);
  assertNotNull(request);
  assertTrue(request.badRequest());
}
