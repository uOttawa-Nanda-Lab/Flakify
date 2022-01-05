@Test public void testDecodeRow2binary_12() throws Exception {
  String expected=" ..X..XX. XXXX..XX X.XX.XX. .X....XX XXX..XX. X..X.... .XX.XX.X .XX.";
  String path="test/data/blackbox/rssexpanded-1/12.png";
  assertCorrectImage2binary(path,expected);
}
