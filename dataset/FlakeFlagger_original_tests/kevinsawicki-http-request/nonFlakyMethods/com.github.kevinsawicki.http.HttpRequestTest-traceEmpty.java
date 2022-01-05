/** 
 * Make a PUT request with an empty body response
 * @throws Exception
 */
@Test public void traceEmpty() throws Exception {
  final AtomicReference<String> method=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      method.set(request.getMethod());
      response.setStatus(HTTP_OK);
    }
  }
;
  HttpRequest request=trace(url);
  assertNotNull(request.getConnection());
  assertTrue(request.ok());
  assertFalse(request.notFound());
  assertEquals("TRACE",method.get());
  assertEquals("",request.body());
}
