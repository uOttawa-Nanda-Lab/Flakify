@Test public void toAsciiLowerCaseNoUppercase() throws Exception {
  ByteString s=ByteString.encodeUtf8("a1_+");
  assertSame(s,s.toAsciiLowercase());
}
