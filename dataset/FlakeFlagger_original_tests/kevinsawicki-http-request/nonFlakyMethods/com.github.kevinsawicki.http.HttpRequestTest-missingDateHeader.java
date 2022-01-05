/** 
 * Verify getting date header with default value
 * @throws Exception
 */
@Test public void missingDateHeader() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
    }
  }
;
  assertEquals(1234L,get(url).dateHeader("missing",1234L));
}
