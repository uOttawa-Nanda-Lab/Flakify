@Test public void testDecodeRow2binary_20() throws Exception {
  String expected=" ..XXXX.X ........ .X..XXX. X.X.X... XX.XXXXX .XXXX.X. ..XX...X .X.....X .XX..... XXXX.X.. XX..";
  String path="test/data/blackbox/rssexpanded-1/20.png";
  assertCorrectImage2binary(path,expected);
}
