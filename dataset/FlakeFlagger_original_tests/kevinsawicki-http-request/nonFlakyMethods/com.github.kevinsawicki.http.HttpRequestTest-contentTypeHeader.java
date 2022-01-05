/** 
 * Verify 'Content-Type' header
 * @throws Exception
 */
@Test public void contentTypeHeader() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.setHeader("Content-Type","text/html");
    }
  }
;
  assertEquals("text/html",get(url).contentType());
}
