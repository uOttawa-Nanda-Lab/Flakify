/** 
 * Verify multipart with content type part header
 * @throws Exception
 */
@Test public void postMultipartWithContentType() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      response.setStatus(HTTP_OK);
      body.set(new String(read()));
    }
  }
;
  HttpRequest request=post(url);
  request.part("body",null,"application/json","contents");
  assertTrue(request.ok());
  assertTrue(body.toString().contains("Content-Type: application/json"));
  assertTrue(body.toString().contains("contents\r\n"));
}
