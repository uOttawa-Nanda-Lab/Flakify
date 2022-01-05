/** 
 * Make a GET request that returns an error string
 * @throws Exception
 */
@Test public void getError() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HttpServletResponse.SC_NOT_FOUND);
      write("error");
    }
  }
;
  HttpRequest request=get(url);
  assertTrue(request.notFound());
  assertEquals("error",request.body());
}
