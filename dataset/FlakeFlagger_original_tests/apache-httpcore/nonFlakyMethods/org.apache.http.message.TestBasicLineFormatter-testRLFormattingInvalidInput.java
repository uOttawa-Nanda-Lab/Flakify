@Test public void testRLFormattingInvalidInput() throws Exception {
  try {
    BasicLineFormatter.formatRequestLine(null,BasicLineFormatter.DEFAULT);
    Assert.fail("IllegalArgumentException should habe been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    BasicLineFormatter.DEFAULT.formatRequestLine(new CharArrayBuffer(10),(RequestLine)null);
    Assert.fail("IllegalArgumentException should habe been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
