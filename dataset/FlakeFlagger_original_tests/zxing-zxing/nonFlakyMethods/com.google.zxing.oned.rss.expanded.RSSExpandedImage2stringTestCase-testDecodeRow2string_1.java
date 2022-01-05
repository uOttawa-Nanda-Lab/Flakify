@Test public void testDecodeRow2string_1() throws Exception {
  String path="test/data/blackbox/rssexpanded-1/1.png";
  String expected="(11)100224(17)110224(3102)000100";
  assertCorrectImage2string(path,expected);
}
