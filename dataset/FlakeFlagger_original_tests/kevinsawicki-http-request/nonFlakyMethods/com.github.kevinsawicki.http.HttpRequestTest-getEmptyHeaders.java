/** 
 * Get header values when not set in response
 * @throws Exception
 */
@Test public void getEmptyHeaders() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
    }
  }
;
  HttpRequest request=get(url);
  assertTrue(request.ok());
  String[] values=request.headers("a");
  assertNotNull(values);
  assertEquals(0,values.length);
}
