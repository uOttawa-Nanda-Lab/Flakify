/** 
 * Verify progress callback when sending a file
 * @throws Exception
 */
@Test public void uploadProgressSend() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      response.setStatus(HTTP_OK);
    }
  }
;
  final File file=File.createTempFile("post",".txt");
  new FileWriter(file).append("hello").close();
  final AtomicLong tx=new AtomicLong(0);
  UploadProgress progress=new UploadProgress(){
    public void onUpload(    long transferred,    long total){
      assertEquals(file.length(),total);
      assertEquals(tx.incrementAndGet(),transferred);
    }
  }
;
  post(url).bufferSize(1).progress(progress).send(file).code();
  assertEquals(file.length(),tx.get());
}
