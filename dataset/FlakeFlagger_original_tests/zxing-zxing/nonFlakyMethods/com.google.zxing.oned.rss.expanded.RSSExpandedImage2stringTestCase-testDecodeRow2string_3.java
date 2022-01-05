@Test public void testDecodeRow2string_3() throws Exception {
  String path="test/data/blackbox/rssexpanded-1/3.png";
  String expected="(10)12A";
  assertCorrectImage2string(path,expected);
}
