/** 
 * Get header parameter value
 * @throws Exception
 */
@Test public void getEmptyParameter() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.setHeader("a","b;c=");
    }
  }
;
  HttpRequest request=get(url);
  assertTrue(request.ok());
  assertNull(request.parameter("a","c"));
  assertTrue(request.parameters("a").isEmpty());
}
