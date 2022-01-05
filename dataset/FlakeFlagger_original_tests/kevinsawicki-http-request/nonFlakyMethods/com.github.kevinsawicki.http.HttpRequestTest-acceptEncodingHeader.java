/** 
 * Verify 'Accept-Encoding' request header
 * @throws Exception
 */
@Test public void acceptEncodingHeader() throws Exception {
  final AtomicReference<String> header=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      header.set(request.getHeader("Accept-Encoding"));
    }
  }
;
  assertTrue(get(url).acceptEncoding("compress").ok());
  assertEquals("compress",header.get());
}
