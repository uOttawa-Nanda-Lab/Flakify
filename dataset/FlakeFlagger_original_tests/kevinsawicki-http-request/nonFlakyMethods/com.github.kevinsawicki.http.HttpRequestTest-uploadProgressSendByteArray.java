/** 
 * Verify progress callback when sending from a byte array
 * @throws Exception
 */
@Test public void uploadProgressSendByteArray() throws Exception {
  final AtomicReference<String> body=new AtomicReference<String>();
  handler=new RequestHandler(){
    @Override public void handle(    Request request,    HttpServletResponse response){
      body.set(new String(read()));
      response.setStatus(HTTP_OK);
    }
  }
;
  final byte[] bytes="hello".getBytes(CHARSET_UTF8);
  final AtomicLong tx=new AtomicLong(0);
  UploadProgress progress=new UploadProgress(){
    public void onUpload(    long transferred,    long total){
      assertEquals(bytes.length,total);
      assertEquals(tx.incrementAndGet(),transferred);
    }
  }
;
  post(url).bufferSize(1).progress(progress).send(bytes).code();
  assertEquals(bytes.length,tx.get());
}
