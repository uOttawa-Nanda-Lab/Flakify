@Test public void decodeHexOddNumberOfChars() throws Exception {
  try {
    ByteString.decodeHex("aaa");
    fail();
  }
 catch (  IllegalArgumentException expected) {
  }
}
