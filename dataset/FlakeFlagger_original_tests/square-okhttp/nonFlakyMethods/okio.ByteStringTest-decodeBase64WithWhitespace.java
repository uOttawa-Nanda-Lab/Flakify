@Test public void decodeBase64WithWhitespace(){
  assertEquals("\u0000\u0000\u0000",ByteString.decodeBase64(" AA AA ").utf8());
  assertEquals("\u0000\u0000\u0000",ByteString.decodeBase64(" AA A\r\nA ").utf8());
  assertEquals("\u0000\u0000\u0000",ByteString.decodeBase64("AA AA").utf8());
  assertEquals("\u0000\u0000\u0000",ByteString.decodeBase64(" AA AA ").utf8());
  assertEquals("\u0000\u0000\u0000",ByteString.decodeBase64(" AA A\r\nA ").utf8());
  assertEquals("\u0000\u0000\u0000",ByteString.decodeBase64("A    AAA").utf8());
  assertEquals("",ByteString.decodeBase64("    ").utf8());
}
