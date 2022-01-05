@Test public void testInvalidConstructor(){
  ByteArrayOutputStream baos=new ByteArrayOutputStream();
  WritableByteChannel channel=newChannel(baos);
  HttpParams params=new BasicHttpParams();
  SessionOutputBuffer outbuf=new SessionOutputBufferImpl(1024,128,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  try {
    new LengthDelimitedEncoder(null,null,null,10);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new LengthDelimitedEncoder(channel,null,null,10);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new LengthDelimitedEncoder(channel,outbuf,null,10);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new LengthDelimitedEncoder(channel,outbuf,metrics,-10);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
