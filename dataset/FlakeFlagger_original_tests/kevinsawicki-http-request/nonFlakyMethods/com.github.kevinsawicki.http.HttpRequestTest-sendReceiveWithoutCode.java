/** 
 * Verify data is sent when receiving response without first calling {@link HttpRequest#code()}
 * @throws Exception
 */
@Test public void sendReceiveWithoutCode() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      try {
        response.getWriter().write("world");
      }
 catch (      IOException ignored) {
      }
      response.setStatus(HTTP_OK);
    }
  }
;
  HttpRequest request=post(url).ignoreCloseExceptions(false);
  assertEquals("world",request.send("hello").body());
  assertEquals("hello",body.get());
}
