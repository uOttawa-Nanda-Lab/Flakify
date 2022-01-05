/** 
 * Make a GET request that should be compressed
 * @throws Exception
 */
@Test public void getGzipped() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      if (!"gzip".equals(request.getHeader("Accept-Encoding")))       return;
      response.setHeader("Content-Encoding","gzip");
      GZIPOutputStream output;
      try {
        output=new GZIPOutputStream(response.getOutputStream());
      }
 catch (      IOException e) {
        throw new RuntimeException(e);
      }
      try {
        output.write("hello compressed".getBytes(CHARSET_UTF8));
      }
 catch (      IOException e) {
        throw new RuntimeException(e);
      }
 finally {
        try {
          output.close();
        }
 catch (        IOException ignored) {
        }
      }
    }
  }
;
  HttpRequest request=get(url).acceptGzipEncoding().uncompress(true);
  assertTrue(request.ok());
  assertEquals("hello compressed",request.body(CHARSET_UTF8));
}
