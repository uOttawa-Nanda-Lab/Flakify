@Test public void testCodingBeyondContentLimit() throws Exception {
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  WritableByteChannel channel=newChannel(baos);
  HttpParams params=new BasicHttpParams();
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(1024,128,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  LengthDelimitedEncoder encoder=new LengthDelimitedEncoder(channel,outbuf,metrics,16);
  encoder.write(wrap("stuff;"));
  encoder.write(wrap("more stuff; and a lot more stuff"));
  String s=baos.toString("US-ASCII");
  Assert.assertTrue(encoder.isCompleted());
  Assert.assertEquals("stuff;more stuff",s);
}
