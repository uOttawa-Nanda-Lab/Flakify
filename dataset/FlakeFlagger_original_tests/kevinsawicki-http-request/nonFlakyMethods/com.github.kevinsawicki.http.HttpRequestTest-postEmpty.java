/** 
 * Make a POST request with an empty request body
 * @throws Exception
 */
@Test public void postEmpty() throws Exception {
  final AtomicReference<String> method=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      method.set(request.getMethod());
      response.setStatus(HTTP_CREATED);
    }
  }
;
  HttpRequest request=post(url);
  int code=request.code();
  assertEquals("POST",method.get());
  assertFalse(request.ok());
  assertTrue(request.created());
  assertEquals(HTTP_CREATED,code);
}
