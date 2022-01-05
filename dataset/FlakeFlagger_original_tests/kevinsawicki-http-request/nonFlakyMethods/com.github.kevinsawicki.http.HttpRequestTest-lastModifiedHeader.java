/** 
 * Verify 'Last-Modified' header
 * @throws Exception
 */
@Test public void lastModifiedHeader() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.setDateHeader("Last-Modified",555000);
    }
  }
;
  assertEquals(555000,get(url).lastModified());
}
