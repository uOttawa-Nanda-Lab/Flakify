/** 
 * Make a GET request that returns an empty error string
 * @throws Exception
 */
@Test public void noError() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
    }
  }
;
  HttpRequest request=get(url);
  assertTrue(request.ok());
  assertEquals("",request.body());
}
