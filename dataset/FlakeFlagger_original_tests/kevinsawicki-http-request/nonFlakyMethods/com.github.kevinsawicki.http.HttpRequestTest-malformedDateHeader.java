/** 
 * Verify getting date header with default value
 * @throws Exception
 */
@Test public void malformedDateHeader() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.setHeader("malformed","not a date");
    }
  }
;
  assertEquals(1234L,get(url).dateHeader("malformed",1234L));
}
