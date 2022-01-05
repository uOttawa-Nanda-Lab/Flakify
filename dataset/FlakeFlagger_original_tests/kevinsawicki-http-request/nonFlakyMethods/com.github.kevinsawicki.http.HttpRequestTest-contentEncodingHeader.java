/** 
 * Verify 'Content-Encoding' header
 * @throws Exception
 */
@Test public void contentEncodingHeader() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.setHeader("Content-Encoding","gzip");
    }
  }
;
  assertEquals("gzip",get(url).contentEncoding());
}
