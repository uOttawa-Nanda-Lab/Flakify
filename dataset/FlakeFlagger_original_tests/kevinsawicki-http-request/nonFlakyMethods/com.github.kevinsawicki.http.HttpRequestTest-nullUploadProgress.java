/** 
 * Verify progress callback doesn't cause an exception when it's null
 * @throws Exception
 */
@Test public void nullUploadProgress() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      response.setStatus(HTTP_OK);
    }
  }
;
  File file=File.createTempFile("post",".txt");
  new FileWriter(file).append("hello").close();
  int code=post(url).progress(null).send(file).code();
  assertEquals(HTTP_OK,code);
  assertEquals("hello",body.get());
}
