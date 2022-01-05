/** 
 * Make a post with an explicit set of the content length
 * @throws Exception
 */
@Test public void postWithLength() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  final AtomicReference<Integer> length=new AtomicReference<Integer>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      length.set(request.getContentLength());
      response.setStatus(HTTP_OK);
    }
  }
;
  String data="hello";
  int sent=data.getBytes().length;
  int code=post(url).contentLength(sent).send(data).code();
  assertEquals(HTTP_OK,code);
  assertEquals(sent,length.get().intValue());
  assertEquals(data,body.get());
}
