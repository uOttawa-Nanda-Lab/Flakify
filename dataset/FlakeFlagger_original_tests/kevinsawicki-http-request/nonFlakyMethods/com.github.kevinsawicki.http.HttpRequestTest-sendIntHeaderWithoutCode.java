/** 
 * Verify data is send when receiving response integer header without first calling  {@link HttpRequest#code()}
 * @throws Exception
 */
@Test public void sendIntHeaderWithoutCode() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      response.setIntHeader("Width",9876);
      response.setStatus(HTTP_OK);
    }
  }
;
  HttpRequest request=post(url).ignoreCloseExceptions(false);
  assertEquals(9876,request.send("hello").intHeader("Width"));
  assertEquals("hello",body.get());
}
