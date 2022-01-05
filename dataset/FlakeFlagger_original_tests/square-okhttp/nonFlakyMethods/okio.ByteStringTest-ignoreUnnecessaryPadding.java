@Test public void ignoreUnnecessaryPadding(){
  assertEquals("",ByteString.decodeBase64("====").utf8());
  assertEquals("\u0000\u0000\u0000",ByteString.decodeBase64("AAAA====").utf8());
}
