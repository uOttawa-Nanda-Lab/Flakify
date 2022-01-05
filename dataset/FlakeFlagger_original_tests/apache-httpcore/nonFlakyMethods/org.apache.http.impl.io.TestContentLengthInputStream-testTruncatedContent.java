@Test public void testTruncatedContent() throws IOException {
  String correct="1234567890123456";
  SessionInputBuffer inbuffer=new SessionInputBufferMock(EncodingUtils.getBytes(correct,CONTENT_CHARSET));
  InputStream in=new ContentLengthInputStream(inbuffer,32L);
  byte[] tmp=new byte[32];
  int byteRead=in.read(tmp);
  Assert.assertEquals(16,byteRead);
  try {
    in.read(tmp);
    Assert.fail("ConnectionClosedException should have been closed");
  }
 catch (  ConnectionClosedException ex) {
  }
  try {
    in.read();
    Assert.fail("ConnectionClosedException should have been closed");
  }
 catch (  ConnectionClosedException ex) {
  }
}
