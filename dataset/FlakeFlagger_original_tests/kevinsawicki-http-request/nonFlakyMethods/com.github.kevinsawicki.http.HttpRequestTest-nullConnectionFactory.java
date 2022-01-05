/** 
 * Verify setting a null connection factory restores to the default one
 */
@Test public void nullConnectionFactory() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
    }
  }
;
  HttpRequest.setConnectionFactory(null);
  int code=get(url).code();
  assertEquals(200,code);
}
