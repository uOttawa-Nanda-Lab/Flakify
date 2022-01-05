@Test public void testInvalidConstructorInput() throws Exception {
  try {
    new HttpResponseParser(null,BasicLineParser.DEFAULT,new DefaultHttpResponseFactory(),new BasicHttpParams());
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    SessionInputBuffer inbuffer=new SessionInputBufferMock(new byte[]{});
    new HttpResponseParser(inbuffer,BasicLineParser.DEFAULT,null,new BasicHttpParams());
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    SessionInputBuffer inbuffer=new SessionInputBufferMock(new byte[]{});
    new HttpResponseParser(inbuffer,BasicLineParser.DEFAULT,new DefaultHttpResponseFactory(),null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
