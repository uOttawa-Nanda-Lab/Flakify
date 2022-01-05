@Test public void testDecodeRow2binary_22() throws Exception {
  String expected=" ..XXXXXX ........ .X..XXX. X.X.X... XX.XXXXX .XXXX.X. ..XX...X .X.....X .XX..... XXXX.X.. XX..";
  String path="test/data/blackbox/rssexpanded-1/22.png";
  assertCorrectImage2binary(path,expected);
}
