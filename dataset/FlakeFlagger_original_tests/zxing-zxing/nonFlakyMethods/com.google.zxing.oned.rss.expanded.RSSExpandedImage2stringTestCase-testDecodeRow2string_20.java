@Test public void testDecodeRow2string_20() throws Exception {
  String expected="(01)90012345678908(3202)001750(15)100312";
  String path="test/data/blackbox/rssexpanded-1/20.png";
  assertCorrectImage2string(path,expected);
}
