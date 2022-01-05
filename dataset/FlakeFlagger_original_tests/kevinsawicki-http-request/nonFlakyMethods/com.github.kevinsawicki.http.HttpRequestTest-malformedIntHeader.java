/** 
 * Verify getting int header with default value
 * @throws Exception
 */
@Test public void malformedIntHeader() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.setHeader("malformed","not an integer");
    }
  }
;
  assertEquals(4321,get(url).intHeader("malformed",4321));
}
