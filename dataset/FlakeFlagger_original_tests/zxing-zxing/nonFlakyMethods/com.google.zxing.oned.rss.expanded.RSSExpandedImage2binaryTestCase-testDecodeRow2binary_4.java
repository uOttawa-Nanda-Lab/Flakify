@Test public void testDecodeRow2binary_4() throws Exception {
  String path="test/data/blackbox/rssexpanded-1/4.png";
  String expected=" ..XXXX.X XX.XXXX. .XXX.XX. XX..X... .XXXXX.. XX.X..X. ..XX..XX XX.X.XXX X..XX..X .X.XXXXX XXXX";
  assertCorrectImage2binary(path,expected);
}
