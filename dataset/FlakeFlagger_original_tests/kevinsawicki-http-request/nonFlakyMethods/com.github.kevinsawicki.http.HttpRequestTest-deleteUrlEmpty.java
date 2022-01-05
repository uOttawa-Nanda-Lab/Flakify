/** 
 * Make a DELETE request with an empty body response
 * @throws Exception
 */
@Test public void deleteUrlEmpty() throws Exception {
  final AtomicReference<String> method=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      method.set(request.getMethod());
      response.setStatus(HTTP_OK);
    }
  }
;
  HttpRequest request=delete(new URL(url));
  assertNotNull(request.getConnection());
  assertTrue(request.ok());
  assertFalse(request.notFound());
  assertEquals("DELETE",method.get());
  assertEquals("",request.body());
}
