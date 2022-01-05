@Test public void testCodingCompleted() throws Exception {
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  WritableByteChannel channel=newChannel(baos);
  HttpParams params=new BasicHttpParams();
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(1024,128,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  LengthDelimitedEncoder encoder=new LengthDelimitedEncoder(channel,outbuf,metrics,5);
  encoder.write(wrap("stuff"));
  try {
    encoder.write(wrap("more stuff"));
    Assert.fail("IllegalStateException should have been thrown");
  }
 catch (  IllegalStateException ex) {
  }
}
