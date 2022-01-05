/** 
 * Make a post in chunked mode
 * @throws Exception
 */
@Test public void chunkPost() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  final AtomicReference<String> encoding=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      response.setStatus(HTTP_OK);
      encoding.set(request.getHeader("Transfer-Encoding"));
    }
  }
;
  String data="hello";
  int code=post(url).chunk(2).send(data).code();
  assertEquals(HTTP_OK,code);
  assertEquals(data,body.get());
  assertEquals("chunked",encoding.get());
}
