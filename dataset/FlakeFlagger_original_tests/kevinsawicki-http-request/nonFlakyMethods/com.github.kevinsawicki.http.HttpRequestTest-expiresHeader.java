/** 
 * Verify 'Expires' header
 * @throws Exception
 */
@Test public void expiresHeader() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.setDateHeader("Expires",1234000);
    }
  }
;
  assertEquals(1234000,get(url).expires());
}
