/** 
 * Verify 'If-Modified-Since' request header
 * @throws Exception
 */
@Test public void ifModifiedSinceHeader() throws Exception {
  final AtomicLong header=new AtomicLong();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      header.set(request.getDateHeader("If-Modified-Since"));
    }
  }
;
  assertTrue(get(url).ifModifiedSince(5000).ok());
  assertEquals(5000,header.get());
}
