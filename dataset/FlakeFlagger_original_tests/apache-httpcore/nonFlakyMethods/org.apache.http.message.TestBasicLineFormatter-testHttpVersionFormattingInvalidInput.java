@Test public void testHttpVersionFormattingInvalidInput() throws Exception {
  try {
    BasicLineFormatter.formatProtocolVersion(null,BasicLineFormatter.DEFAULT);
    Assert.fail("IllegalArgumentException should habe been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    BasicLineFormatter.DEFAULT.appendProtocolVersion(new CharArrayBuffer(10),(HttpVersion)null);
    Assert.fail("IllegalArgumentException should habe been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
