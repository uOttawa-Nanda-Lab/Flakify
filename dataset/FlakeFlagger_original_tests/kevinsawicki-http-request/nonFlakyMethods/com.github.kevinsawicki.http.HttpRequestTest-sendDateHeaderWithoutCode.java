/** 
 * Verify data is send when receiving response date header without first calling  {@link HttpRequest#code()}
 * @throws Exception
 */
@Test public void sendDateHeaderWithoutCode() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      response.setDateHeader("Date",1000);
      response.setStatus(HTTP_OK);
    }
  }
;
  HttpRequest request=post(url).ignoreCloseExceptions(false);
  assertEquals(1000,request.send("hello").date());
  assertEquals("hello",body.get());
}
