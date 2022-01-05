@Test public void testDecodeRow2binary_17() throws Exception {
  String expected=" ..XXX.X. ........ .X..XXX. X.X.X... XX.XXXXX .XXXX.X. ..XX...X .X.....X .XX..... XXXX.X.. XX..";
  String path="test/data/blackbox/rssexpanded-1/17.png";
  assertCorrectImage2binary(path,expected);
}
