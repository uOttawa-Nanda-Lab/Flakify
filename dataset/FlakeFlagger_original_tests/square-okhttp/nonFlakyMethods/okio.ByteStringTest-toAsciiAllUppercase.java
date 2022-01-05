@Test public void toAsciiAllUppercase() throws Exception {
  assertEquals(ByteString.encodeUtf8("ab"),ByteString.encodeUtf8("AB").toAsciiLowercase());
}
