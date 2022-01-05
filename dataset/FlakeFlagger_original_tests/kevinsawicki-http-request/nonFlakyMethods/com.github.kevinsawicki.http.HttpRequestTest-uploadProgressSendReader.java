/** 
 * Verify progress callback when sending from a Reader
 * @throws Exception
 */
@Test public void uploadProgressSendReader() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      response.setStatus(HTTP_OK);
    }
  }
;
  final AtomicLong tx=new AtomicLong(0);
  UploadProgress progress=new UploadProgress(){
    public void onUpload(    long transferred,    long total){
      assertEquals(-1,total);
      assertEquals(tx.incrementAndGet(),transferred);
    }
  }
;
  File file=File.createTempFile("post",".txt");
  new FileWriter(file).append("hello").close();
  post(url).progress(progress).bufferSize(1).send(new FileReader(file)).code();
  assertEquals(file.length(),tx.get());
}
