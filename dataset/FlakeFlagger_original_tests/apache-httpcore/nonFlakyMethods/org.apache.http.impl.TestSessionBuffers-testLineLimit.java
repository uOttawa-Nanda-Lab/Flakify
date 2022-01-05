@Test public void testLineLimit() throws Exception {
  HttpParams params=new BasicHttpParams();
  String s="a very looooooooooooooooooooooooooooooooooooooong line\r\n     ";
  byte[] tmp=s.getBytes("US-ASCII");
  params.setIntParameter(CoreConnectionPNames.MAX_LINE_LENGTH,0);
  SessionInputBufferMock inbuffer1=new SessionInputBufferMock(tmp,5,params);
  Assert.assertNotNull(inbuffer1.readLine());
  long bytesRead=inbuffer1.getMetrics().getBytesTransferred();
  Assert.assertEquals(60,bytesRead);
  params.setIntParameter(CoreConnectionPNames.MAX_LINE_LENGTH,15);
  SessionInputBufferMock inbuffer2=new SessionInputBufferMock(tmp,5,params);
  try {
    inbuffer2.readLine();
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException ex) {
    bytesRead=inbuffer2.getMetrics().getBytesTransferred();
    Assert.assertEquals(20,bytesRead);
  }
}
