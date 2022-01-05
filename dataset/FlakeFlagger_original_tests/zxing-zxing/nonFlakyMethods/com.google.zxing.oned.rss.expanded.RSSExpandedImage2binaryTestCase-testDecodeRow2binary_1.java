@Test public void testDecodeRow2binary_1() throws Exception {
  String path="test/data/blackbox/rssexpanded-1/1.png";
  String expected=" ...X...X .X....X. .XX...X. X..X...X ...XX.X. ..X.X... ..X.X..X ...X..X. X.X....X .X....X. .....X.. X...X...";
  assertCorrectImage2binary(path,expected);
}
