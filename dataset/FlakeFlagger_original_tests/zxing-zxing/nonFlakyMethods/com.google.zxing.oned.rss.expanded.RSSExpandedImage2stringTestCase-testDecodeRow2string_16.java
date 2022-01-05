@Test public void testDecodeRow2string_16() throws Exception {
  String expected="(01)90012345678908(3202)001750(11)100312";
  String path="test/data/blackbox/rssexpanded-1/16.png";
  assertCorrectImage2string(path,expected);
}
