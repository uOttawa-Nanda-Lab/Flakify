@Test public void testDecodeRow2binary_19() throws Exception {
  String expected=" ..XXXX.. ........ .X..XXX. X.X.X... XX.XXXXX .XXXX.X. ..XX...X .X.....X .XX..... XXXX.X.. XX..";
  String path="test/data/blackbox/rssexpanded-1/19.png";
  assertCorrectImage2binary(path,expected);
}
