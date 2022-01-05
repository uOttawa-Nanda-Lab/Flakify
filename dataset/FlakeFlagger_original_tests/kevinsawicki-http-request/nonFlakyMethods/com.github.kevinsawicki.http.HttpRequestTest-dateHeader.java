/** 
 * Verify 'Date' header
 * @throws Exception
 */
@Test public void dateHeader() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.setDateHeader("Date",66000);
    }
  }
;
  assertEquals(66000,get(url).date());
}
