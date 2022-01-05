@Test public void testHeaderFormattingInvalidInput() throws Exception {
  try {
    BasicLineFormatter.formatHeader(null,BasicLineFormatter.DEFAULT);
    Assert.fail("IllegalArgumentException should habe been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    BasicLineFormatter.DEFAULT.formatHeader(new CharArrayBuffer(10),(Header)null);
    Assert.fail("IllegalArgumentException should habe been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
