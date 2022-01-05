/** 
 * Verify progress callback when sending from an InputStream
 * @throws Exception
 */
@Test public void uploadProgressSendInputStream() throws Exception {
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
  InputStream input=new FileInputStream(file);
  final AtomicLong tx=new AtomicLong(0);
  UploadProgress progress=new UploadProgress(){
    public void onUpload(    long transferred,    long total){
      assertEquals(-1,total);
      assertEquals(tx.incrementAndGet(),transferred);
    }
  }
;
  post(url).bufferSize(1).progress(progress).send(input).code();
  assertEquals(file.length(),tx.get());
}
