/** 
 * Get header with multiple response values
 * @throws Exception
 */
@Test public void getHeaders() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.addHeader("a","1");
      response.addHeader("a","2");
    }
  }
;
  HttpRequest request=get(url);
  assertTrue(request.ok());
  String[] values=request.headers("a");
  assertNotNull(values);
  assertEquals(2,values.length);
  assertTrue(Arrays.asList(values).contains("1"));
  assertTrue(Arrays.asList(values).contains("2"));
}
