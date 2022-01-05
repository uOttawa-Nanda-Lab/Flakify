/** 
 * Make a DELETE request with an empty body response
 * @throws Exception
 */
@Test public void deleteEmpty() throws Exception {
  final AtomicReference<String> method=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      method.set(request.getMethod());
      response.setStatus(HTTP_OK);
    }
  }
;
  HttpRequest request=delete(url);
  assertNotNull(request.getConnection());
  assertTrue(request.ok());
  assertFalse(request.notFound());
  assertEquals("DELETE",method.get());
  assertEquals("",request.body());
  assertEquals("DELETE",request.method());
}
