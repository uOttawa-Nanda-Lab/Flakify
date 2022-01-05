@Test public void testDecodeRow2binary_2() throws Exception {
  String path="test/data/blackbox/rssexpanded-1/2.png";
  String expected=" ..X..... ......X. .XXX.X.X .X...XX. XXXXX.XX XX.X.... .XX.XX.X .XX.";
  assertCorrectImage2binary(path,expected);
}
