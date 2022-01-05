@Test public void testBasicCoding() throws Exception {
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  WritableByteChannel channel=newChannel(baos);
  HttpParams params=new BasicHttpParams();
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(1024,128,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  IdentityEncoder encoder=new IdentityEncoder(channel,outbuf,metrics);
  encoder.write(wrap("stuff"));
  encoder.complete();
  String s=baos.toString("US-ASCII");
  Assert.assertTrue(encoder.isCompleted());
  Assert.assertEquals("stuff",s);
}
