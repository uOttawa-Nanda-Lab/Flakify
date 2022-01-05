/** 
 * Make a GET request with a response that includes a charset parameter
 * @throws Exception
 */
@Test public void getWithResponseCharsetAsSecondParam() throws Exception {
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      response.setContentType("text/html; param1=val1; charset=UTF-8");
    }
  }
;
  HttpRequest request=get(url);
  assertEquals(HTTP_OK,request.code());
  assertEquals(CHARSET_UTF8,request.charset());
}
