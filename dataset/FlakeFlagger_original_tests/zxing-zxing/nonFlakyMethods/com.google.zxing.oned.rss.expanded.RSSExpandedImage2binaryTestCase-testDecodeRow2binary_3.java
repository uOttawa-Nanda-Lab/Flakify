@Test public void testDecodeRow2binary_3() throws Exception {
  String path="test/data/blackbox/rssexpanded-1/3.png";
  String expected=" .......X ..XX..X. X.X....X .......X ....";
  assertCorrectImage2binary(path,expected);
}
