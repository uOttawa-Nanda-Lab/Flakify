/** 
 * Verify setting number header
 * @throws Exception
 */
@Test public void numberHeader() throws Exception {
  final AtomicReference<String> h1=new AtomicReference<String>();
  final AtomicReference<String> h2=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      h1.set(request.getHeader("h1"));
      h2.set(request.getHeader("h2"));
    }
  }
;
  assertTrue(get(url).header("h1",5).header("h2",(Number)null).ok());
  assertEquals("5",h1.get());
  assertEquals("",h2.get());
}
