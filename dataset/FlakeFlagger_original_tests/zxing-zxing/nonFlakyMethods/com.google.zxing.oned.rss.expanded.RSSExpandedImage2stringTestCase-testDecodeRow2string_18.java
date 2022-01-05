@Test public void testDecodeRow2string_18() throws Exception {
  String expected="(01)90012345678908(3202)001750(13)100312";
  String path="test/data/blackbox/rssexpanded-1/18.png";
  assertCorrectImage2string(path,expected);
}
