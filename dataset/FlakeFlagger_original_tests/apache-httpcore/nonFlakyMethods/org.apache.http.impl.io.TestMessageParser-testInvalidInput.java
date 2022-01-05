@Test public void testInvalidInput() throws Exception {
  try {
    AbstractMessageParser.parseHeaders(null,-1,-1,null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    new BufferedHeader(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
