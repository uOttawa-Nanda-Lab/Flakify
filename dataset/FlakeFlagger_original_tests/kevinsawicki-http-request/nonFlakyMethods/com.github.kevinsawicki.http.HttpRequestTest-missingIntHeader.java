/** 
 * Verify getting int header with default value
 * @throws Exception
 */
@Test public void missingIntHeader() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
    }
  }
;
  assertEquals(4321,get(url).intHeader("missing",4321));
}
