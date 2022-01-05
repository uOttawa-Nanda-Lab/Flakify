/** 
 * Make a GET request that should be compressed but isn't
 * @throws Exception
 */
@Test public void getNonGzippedWithUncompressEnabled() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      if (!"gzip".equals(request.getHeader("Accept-Encoding")))       return;
      write("hello not compressed");
    }
  }
;
  HttpRequest request=get(url).acceptGzipEncoding().uncompress(true);
  assertTrue(request.ok());
  assertEquals("hello not compressed",request.body(CHARSET_UTF8));
}
