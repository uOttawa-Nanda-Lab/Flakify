/** 
 * Make a POST request with a non-empty request body
 * @throws Exception
 */
@Test public void postNonEmptyString() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      response.setStatus(HTTP_OK);
    }
  }
;
  int code=post(url).send("hello").code();
  assertEquals(HTTP_OK,code);
  assertEquals("hello",body.get());
}
