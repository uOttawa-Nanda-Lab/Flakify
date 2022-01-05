/** 
 * Verify 'Accept' request header when calling {@link HttpRequest#acceptJson()}
 * @throws Exception
 */
@Test public void acceptJson() throws Exception {
  final AtomicReference<String> header=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      header.set(request.getHeader("Accept"));
    }
  }
;
  assertTrue(get(url).acceptJson().ok());
  assertEquals("application/json",header.get());
}
