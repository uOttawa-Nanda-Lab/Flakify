/** 
 * Make a POST request with a non-empty request body
 * @throws Exception
 */
@Test public void postNonEmptyByteArray() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      response.setStatus(HTTP_OK);
    }
  }
;
  byte[] bytes="hello".getBytes(CHARSET_UTF8);
  int code=post(url).contentLength(Integer.toString(bytes.length)).send(bytes).code();
  assertEquals(HTTP_OK,code);
  assertEquals("hello",body.get());
}
