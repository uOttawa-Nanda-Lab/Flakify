@Test public void toAsciiStartsLowercaseEndsUppercase() throws Exception {
  assertEquals(ByteString.encodeUtf8("abcd"),ByteString.encodeUtf8("abCD").toAsciiLowercase());
}
