@Test public void testDecodeRow2string_19() throws Exception {
  String expected="(01)90012345678908(3102)001750(15)100312";
  String path="test/data/blackbox/rssexpanded-1/19.png";
  assertCorrectImage2string(path,expected);
}
