/** 
 * Verify data is send when receiving response headers without first calling {@link HttpRequest#code()}
 * @throws Exception
 */
@Test public void sendHeadersWithoutCode() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      response.setHeader("h1","v1");
      response.setHeader("h2","v2");
      response.setStatus(HTTP_OK);
    }
  }
;
  HttpRequest request=post(url).ignoreCloseExceptions(false);
  Map<String,List<String>> headers=request.send("hello").headers();
  assertEquals("v1",headers.get("h1").get(0));
  assertEquals("v2",headers.get("h2").get(0));
  assertEquals("hello",body.get());
}
