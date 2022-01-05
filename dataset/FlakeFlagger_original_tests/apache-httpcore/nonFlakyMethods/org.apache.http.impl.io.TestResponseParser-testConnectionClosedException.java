@Test public void testConnectionClosedException() throws Exception {
  SessionInputBuffer inbuffer=new SessionInputBufferMock(new byte[]{});
  HttpResponseParser parser=new HttpResponseParser(inbuffer,BasicLineParser.DEFAULT,new DefaultHttpResponseFactory(),new BasicHttpParams());
  try {
    parser.parse();
    Assert.fail("NoHttpResponseException should have been thrown");
  }
 catch (  NoHttpResponseException expected) {
  }
}
