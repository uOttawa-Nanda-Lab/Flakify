/** 
 * Verify 'Accept' request header
 * @throws Exception
 */
@Test public void acceptHeader() throws Exception {
  final AtomicReference<String> header=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      header.set(request.getHeader("Accept"));
    }
  }
;
  assertTrue(get(url).accept("application/json").ok());
  assertEquals("application/json",header.get());
}
