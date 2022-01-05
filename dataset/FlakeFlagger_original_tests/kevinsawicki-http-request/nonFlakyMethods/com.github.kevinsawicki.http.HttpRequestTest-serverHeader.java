/** 
 * Verify 'Server' header
 * @throws Exception
 */
@Test public void serverHeader() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.setHeader("Server","aserver");
    }
  }
;
  assertEquals("aserver",get(url).server());
}
