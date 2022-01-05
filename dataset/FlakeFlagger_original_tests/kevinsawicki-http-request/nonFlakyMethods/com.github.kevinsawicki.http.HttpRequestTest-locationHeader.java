/** 
 * Verify 'Location' header
 * @throws Exception
 */
@Test public void locationHeader() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.setHeader("Location","http://nowhere");
    }
  }
;
  assertEquals("http://nowhere",get(url).location());
}
