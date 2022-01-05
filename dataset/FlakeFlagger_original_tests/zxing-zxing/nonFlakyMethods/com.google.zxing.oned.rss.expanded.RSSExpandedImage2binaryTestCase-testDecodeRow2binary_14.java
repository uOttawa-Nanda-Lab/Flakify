@Test public void testDecodeRow2binary_14() throws Exception {
  String expected=" ..XX.X.. ........ .X..XXX. X.X.X... XX.XXXXX .XXXX.X. X.....X. X.....X. X.X.X.XX .X...... X...";
  String path="test/data/blackbox/rssexpanded-1/14.png";
  assertCorrectImage2binary(path,expected);
}
