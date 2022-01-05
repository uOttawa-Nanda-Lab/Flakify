@Test public void testConnectionClosedException() throws Exception {
  SessionInputBuffer inbuffer=new SessionInputBufferMock(new byte[]{});
  HttpRequestParser parser=new HttpRequestParser(inbuffer,BasicLineParser.DEFAULT,new DefaultHttpRequestFactory(),new BasicHttpParams());
  try {
    parser.parse();
    Assert.fail("ConnectionClosedException should have been thrown");
  }
 catch (  ConnectionClosedException expected) {
  }
}
