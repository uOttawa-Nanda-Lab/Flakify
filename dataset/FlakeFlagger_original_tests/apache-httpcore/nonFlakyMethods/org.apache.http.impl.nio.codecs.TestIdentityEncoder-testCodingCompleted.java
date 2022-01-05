@Test public void testCodingCompleted() throws Exception {
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  WritableByteChannel channel=newChannel(baos);
  HttpParams params=new BasicHttpParams();
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(1024,128,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  IdentityEncoder encoder=new IdentityEncoder(channel,outbuf,metrics);
  encoder.write(wrap("stuff"));
  encoder.complete();
  try {
    encoder.write(wrap("more stuff"));
    Assert.fail("IllegalStateException should have been thrown");
  }
 catch (  IllegalStateException ex) {
  }
}
