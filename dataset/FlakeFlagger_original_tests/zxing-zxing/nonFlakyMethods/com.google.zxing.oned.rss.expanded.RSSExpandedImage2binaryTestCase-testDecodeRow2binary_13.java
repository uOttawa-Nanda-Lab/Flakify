@Test public void testDecodeRow2binary_13() throws Exception {
  String expected=" ..XX..X. ........ .X..XXX. X.X.X... XX.XXXXX .XXXX.X. X.X.XXXX .X..X..X ......X.";
  String path="test/data/blackbox/rssexpanded-1/13.png";
  assertCorrectImage2binary(path,expected);
}
