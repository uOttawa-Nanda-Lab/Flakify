/** 
 * Make a GET request with an empty body response
 * @throws Exception
 */
@Test public void getEmpty() throws Exception {
  final AtomicReference<String> method=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      method.set(request.getMethod());
      response.setStatus(HTTP_OK);
    }
  }
;
  HttpRequest request=get(url);
  assertNotNull(request.getConnection());
  assertEquals(30000,request.readTimeout(30000).getConnection().getReadTimeout());
  assertEquals(50000,request.connectTimeout(50000).getConnection().getConnectTimeout());
  assertEquals(2500,request.bufferSize(2500).bufferSize());
  assertFalse(request.ignoreCloseExceptions(false).ignoreCloseExceptions());
  assertFalse(request.useCaches(false).getConnection().getUseCaches());
  int code=request.code();
  assertTrue(request.ok());
  assertFalse(request.created());
  assertFalse(request.badRequest());
  assertFalse(request.serverError());
  assertFalse(request.notFound());
  assertFalse(request.notModified());
  assertEquals("GET",method.get());
  assertEquals("OK",request.message());
  assertEquals(HTTP_OK,code);
  assertEquals("",request.body());
  assertNotNull(request.toString());
  assertFalse(request.toString().length() == 0);
  assertEquals(request,request.disconnect());
  assertTrue(request.isBodyEmpty());
  assertEquals(request.url().toString(),url);
  assertEquals("GET",request.method());
}
