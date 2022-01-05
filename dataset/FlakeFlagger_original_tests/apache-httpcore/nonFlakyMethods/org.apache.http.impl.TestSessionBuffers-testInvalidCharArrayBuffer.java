@Test public void testInvalidCharArrayBuffer() throws Exception {
  SessionInputBufferMock inbuffer=new SessionInputBufferMock(new byte[]{});
  try {
    inbuffer.readLine(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
    long bytesRead=inbuffer.getMetrics().getBytesTransferred();
    Assert.assertEquals(0,bytesRead);
  }
}
