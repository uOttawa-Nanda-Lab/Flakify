@Test public void testDecodeRow2binary_18() throws Exception {
  String expected=" ..XXX.XX ........ .X..XXX. X.X.X... XX.XXXXX .XXXX.X. ..XX...X .X.....X .XX..... XXXX.X.. XX..";
  String path="test/data/blackbox/rssexpanded-1/18.png";
  assertCorrectImage2binary(path,expected);
}
