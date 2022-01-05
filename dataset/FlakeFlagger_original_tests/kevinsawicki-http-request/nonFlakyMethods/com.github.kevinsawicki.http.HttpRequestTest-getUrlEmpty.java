/** 
 * Make a GET request with an empty body response
 * @throws Exception
 */
@Test public void getUrlEmpty() throws Exception {
  final AtomicReference<String> method=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      method.set(request.getMethod());
      response.setStatus(HTTP_OK);
    }
  }
;
  HttpRequest request=get(new URL(url));
  assertNotNull(request.getConnection());
  int code=request.code();
  assertTrue(request.ok());
  assertFalse(request.created());
  assertFalse(request.noContent());
  assertFalse(request.badRequest());
  assertFalse(request.serverError());
  assertFalse(request.notFound());
  assertEquals("GET",method.get());
  assertEquals("OK",request.message());
  assertEquals(HTTP_OK,code);
  assertEquals("",request.body());
}
