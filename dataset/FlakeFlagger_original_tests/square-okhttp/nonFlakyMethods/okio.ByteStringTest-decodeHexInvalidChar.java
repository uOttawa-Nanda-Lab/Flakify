@Test public void decodeHexInvalidChar() throws Exception {
  try {
    ByteString.decodeHex("a\u0000");
    fail();
  }
 catch (  IllegalArgumentException expected) {
  }
}
