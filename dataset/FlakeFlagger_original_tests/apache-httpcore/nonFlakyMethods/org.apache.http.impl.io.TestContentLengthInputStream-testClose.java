@Test public void testClose() throws IOException {
  String correct="1234567890123456-";
  SessionInputBuffer inbuffer=new SessionInputBufferMock(EncodingUtils.getBytes(correct,CONTENT_CHARSET));
  InputStream in=new ContentLengthInputStream(inbuffer,16L);
  in.close();
  in.close();
  try {
    in.read();
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException ex) {
  }
  byte[] tmp=new byte[10];
  try {
    in.read(tmp);
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException ex) {
  }
  try {
    in.read(tmp,0,tmp.length);
    Assert.fail("IOException should have been thrown");
  }
 catch (  IOException ex) {
  }
  Assert.assertEquals('-',inbuffer.read());
}
