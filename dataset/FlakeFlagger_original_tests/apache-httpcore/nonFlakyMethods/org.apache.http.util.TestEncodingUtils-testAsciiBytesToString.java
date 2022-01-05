@Test public void testAsciiBytesToString() throws Exception {
  String s="ascii only, I mean it!";
  Assert.assertEquals(s,EncodingUtils.getAsciiString(s.getBytes("US-ASCII")));
  try {
    EncodingUtils.getAsciiString(null);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
  try {
    EncodingUtils.getAsciiString(null,0,0);
    Assert.fail("IllegalArgumentException should have been thrown");
  }
 catch (  IllegalArgumentException ex) {
  }
}
