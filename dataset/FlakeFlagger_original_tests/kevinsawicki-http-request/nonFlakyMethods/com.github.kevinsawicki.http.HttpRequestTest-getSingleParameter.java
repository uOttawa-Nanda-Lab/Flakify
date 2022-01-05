/** 
 * Get header parameter value
 * @throws Exception
 */
@Test public void getSingleParameter() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.setHeader("a","b;c=d");
    }
  }
;
  HttpRequest request=get(url);
  assertTrue(request.ok());
  assertEquals("d",request.parameter("a","c"));
}
