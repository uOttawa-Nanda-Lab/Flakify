/** 
 * Verify 'If-None-Match' request header
 * @throws Exception
 */
@Test public void ifNoneMatchHeader() throws Exception {
  final AtomicReference<String> header=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      header.set(request.getHeader("If-None-Match"));
    }
  }
;
  assertTrue(get(url).ifNoneMatch("eid").ok());
  assertEquals("eid",header.get());
}
