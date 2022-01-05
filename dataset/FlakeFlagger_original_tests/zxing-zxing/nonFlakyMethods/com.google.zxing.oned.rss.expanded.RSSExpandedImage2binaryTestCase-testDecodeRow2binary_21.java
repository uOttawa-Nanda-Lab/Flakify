@Test public void testDecodeRow2binary_21() throws Exception {
  String expected=" ..XXXXX. ........ .X..XXX. X.X.X... XX.XXXXX .XXXX.X. ..XX...X .X.....X .XX..... XXXX.X.. XX..";
  String path="test/data/blackbox/rssexpanded-1/21.png";
  assertCorrectImage2binary(path,expected);
}
