/** 
 * Verify 'Cache-Control' header
 * @throws Exception
 */
@Test public void cacheControlHeader() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.setHeader("Cache-Control","no-cache");
    }
  }
;
  assertEquals("no-cache",get(url).cacheControl());
}
