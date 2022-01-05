@Test public void testDecodeRow2string_21() throws Exception {
  String expected="(01)90012345678908(3102)001750(17)100312";
  String path="test/data/blackbox/rssexpanded-1/21.png";
  assertCorrectImage2string(path,expected);
}
