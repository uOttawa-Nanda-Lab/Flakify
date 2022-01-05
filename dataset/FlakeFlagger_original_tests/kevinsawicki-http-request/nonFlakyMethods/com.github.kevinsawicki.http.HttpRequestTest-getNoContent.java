/** 
 * Make a GET request with an empty body response
 * @throws Exception
 */
@Test public void getNoContent() throws Exception {
  final AtomicReference<String> method=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      method.set(request.getMethod());
      response.setStatus(HTTP_NO_CONTENT);
    }
  }
;
  HttpRequest request=get(new URL(url));
  assertNotNull(request.getConnection());
  int code=request.code();
  assertFalse(request.ok());
  assertFalse(request.created());
  assertTrue(request.noContent());
  assertFalse(request.badRequest());
  assertFalse(request.serverError());
  assertFalse(request.notFound());
  assertEquals("GET",method.get());
  assertEquals("No Content",request.message());
  assertEquals(HTTP_NO_CONTENT,code);
  assertEquals("",request.body());
}
