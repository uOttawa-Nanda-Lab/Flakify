/** 
 * Verify 'Accept-Charset' request header
 * @throws Exception
 */
@Test public void acceptCharsetHeader() throws Exception {
  final AtomicReference<String> header=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      header.set(request.getHeader("Accept-Charset"));
    }
  }
;
  assertTrue(get(url).acceptCharset(CHARSET_UTF8).ok());
  assertEquals(CHARSET_UTF8,header.get());
}
