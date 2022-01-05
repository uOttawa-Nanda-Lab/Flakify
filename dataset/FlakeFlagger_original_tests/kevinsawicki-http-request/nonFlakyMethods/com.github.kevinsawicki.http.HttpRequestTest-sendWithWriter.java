/** 
 * Make a POST and send request using a writer
 * @throws Exception
 */
@Test public void sendWithWriter() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      response.setStatus(HTTP_OK);
    }
  }
;
  HttpRequest request=post(url);
  request.writer().append("hello").close();
  assertTrue(request.ok());
  assertEquals("hello",body.get());
}
