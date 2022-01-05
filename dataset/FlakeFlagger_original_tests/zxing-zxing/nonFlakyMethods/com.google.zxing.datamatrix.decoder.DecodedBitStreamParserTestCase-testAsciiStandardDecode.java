@Test public void testAsciiStandardDecode() throws Exception {
  byte[] bytes={(byte)('a' + 1),(byte)('b' + 1),(byte)('c' + 1),(byte)('A' + 1),(byte)('B' + 1),(byte)('C' + 1)};
  String decodedString=DecodedBitStreamParser.decode(bytes).getText();
  assertEquals("abcABC",decodedString);
}
