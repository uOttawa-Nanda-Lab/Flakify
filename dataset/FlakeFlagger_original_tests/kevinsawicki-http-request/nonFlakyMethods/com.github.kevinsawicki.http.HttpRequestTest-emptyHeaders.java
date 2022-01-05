/** 
 * Verify setting headers
 * @throws Exception
 */
@Test public void emptyHeaders() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
    }
  }
;
  assertTrue(get(url).headers(Collections.<String,String>emptyMap()).ok());
}
