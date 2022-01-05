@Test public void testSLFormattingInvalidInput() throws Exception {
  try {
    BasicLineFormatter.formatStatusLine(null,BasicLineFormatter.DEFAULT);
    Assert.fail("IllegalArgumentException should habe been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    BasicLineFormatter.DEFAULT.formatStatusLine(new CharArrayBuffer(10),(StatusLine)null);
    Assert.fail("IllegalArgumentException should habe been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
