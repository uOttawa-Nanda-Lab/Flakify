@Test public void testDecodeRow2string_14() throws Exception {
  String expected="(01)90012345678908(3932)0401234";
  String path="test/data/blackbox/rssexpanded-1/14.png";
  assertCorrectImage2string(path,expected);
}
