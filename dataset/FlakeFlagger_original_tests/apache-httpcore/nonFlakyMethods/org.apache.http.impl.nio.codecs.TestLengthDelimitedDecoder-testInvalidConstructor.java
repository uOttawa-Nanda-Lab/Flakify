@Test public void testInvalidConstructor(){
  ReadableByteChannel channel=new ReadableByteChannelMock(new String[]{"stuff;","more stuff"},"US-ASCII");
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,256,params);
  HttpTransportMetricsImpl metrics=new HttpTransportMetricsImpl();
  try {
    new LengthDelimitedDecoder(null,null,null,10);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new LengthDelimitedDecoder(channel,null,null,10);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new LengthDelimitedDecoder(channel,inbuf,null,10);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new LengthDelimitedDecoder(channel,inbuf,metrics,-10);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
