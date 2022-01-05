@Test public void testInvalidConstructor(){
  ReadableByteChannel channel=new ReadableByteChannelMock(new String[]{"stuff;","more stuff"},"US-ASCII");
  HttpParams params=new BasicHttpParams();
  SessionInputBuffer inbuf=new SessionInputBufferImpl(1024,256,params);
  try {
    new IdentityDecoder(null,null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new IdentityDecoder(channel,null,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new IdentityDecoder(channel,inbuf,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
