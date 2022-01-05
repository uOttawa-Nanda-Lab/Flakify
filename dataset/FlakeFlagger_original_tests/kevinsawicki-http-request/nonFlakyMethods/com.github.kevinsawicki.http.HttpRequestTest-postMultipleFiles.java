/** 
 * Make a POST request with multiple files in the body
 * @throws Exception
 */
@Test public void postMultipleFiles() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      response.setStatus(HTTP_OK);
    }
  }
;
  File file1=File.createTempFile("post",".txt");
  new FileWriter(file1).append("hello").close();
  File file2=File.createTempFile("post",".txt");
  new FileWriter(file2).append(" world").close();
  int code=post(url).send(file1).send(file2).code();
  assertEquals(HTTP_OK,code);
  assertEquals("hello world",body.get());
}
