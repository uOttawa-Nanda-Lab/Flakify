@Test public void testDecodeRow2string_2() throws Exception {
  String path="test/data/blackbox/rssexpanded-1/2.png";
  String expected="(01)90012345678908(3103)001750";
  assertCorrectImage2string(path,expected);
}
