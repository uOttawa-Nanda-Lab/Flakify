@Test public void testChunkedInputStreamClose() throws IOException {
  String s="5\r\n01234\r\n5\r\n56789\r\n0\r\n";
  ChunkedInputStream in=new ChunkedInputStream(new SessionInputBufferMock(EncodingUtils.getBytes(s,CONTENT_CHARSET)));
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
}
