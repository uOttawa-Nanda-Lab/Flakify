@Test public void testInvalidConstructorInput() throws Exception {
  try {
    new HttpRequestParser(null,BasicLineParser.DEFAULT,new DefaultHttpRequestFactory(),new BasicHttpParams());
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    SessionInputBuffer inbuffer=new SessionInputBufferMock(new byte[]{});
    new HttpRequestParser(inbuffer,BasicLineParser.DEFAULT,null,new BasicHttpParams());
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    SessionInputBuffer inbuffer=new SessionInputBufferMock(new byte[]{});
    new HttpRequestParser(inbuffer,BasicLineParser.DEFAULT,new DefaultHttpRequestFactory(),null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
