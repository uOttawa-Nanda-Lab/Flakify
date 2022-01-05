@Test public void testDecodeRow2binary_5() throws Exception {
  String path="test/data/blackbox/rssexpanded-1/5.png";
  String expected=" ..X.X... .XXXX.X. XX..XXXX ....XX.. X....... ....X... ....X..X .XX.";
  assertCorrectImage2binary(path,expected);
}
