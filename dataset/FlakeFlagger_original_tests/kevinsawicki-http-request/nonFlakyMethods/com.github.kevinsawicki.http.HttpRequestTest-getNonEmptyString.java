/** 
 * Make a GET request for a non-empty response body
 * @throws Exception
 */
@Test public void getNonEmptyString() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      write("hello");
    }
  }
;
  HttpRequest request=get(url);
  assertEquals(HTTP_OK,request.code());
  assertEquals("hello",request.body());
  assertEquals("hello".getBytes().length,request.contentLength());
  assertFalse(request.isBodyEmpty());
}
