@Test public void testDecodeRow2binary_11() throws Exception {
  String expected=" .X.XX..X XX.XXXX. .XXX.XX. XX..X... .XXXXX.. XX.X..X. ..XX...X XX.X.... X.X.X.X. X.X..X.X .X....X. XX...X.. ...XX.X. .XXXXXX. .X..XX.. X.X.X... .X...... XXXX.... XX.XX... XXXXX.X. ...XXXXX .....X.X ...X.... X.XXX..X X.X.X... ....";
  String path="test/data/blackbox/rssexpanded-1/11.png";
  assertCorrectImage2binary(path,expected);
}
