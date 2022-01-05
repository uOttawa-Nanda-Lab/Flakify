@Test public void testCodingCompleted() throws Exception {
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  WritableByteChannel channel=newChannel(baos);
  HttpParams params=new BasicHttpParams();
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(1024,128,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  ChunkEncoder encoder=new ChunkEncoder(channel,outbuf,metrics);
  encoder.write(wrap("12345"));
  encoder.write(wrap("678"));
  encoder.write(wrap("90"));
  encoder.complete();
  try {
    encoder.write(wrap("more stuff"));
    Assert.fail("IllegalStateException should have been thrown");
  }
 catch (  IllegalStateException ex) {
  }
  try {
    encoder.complete();
    Assert.fail("IllegalStateException should have been thrown");
  }
 catch (  IllegalStateException ex) {
  }
}
