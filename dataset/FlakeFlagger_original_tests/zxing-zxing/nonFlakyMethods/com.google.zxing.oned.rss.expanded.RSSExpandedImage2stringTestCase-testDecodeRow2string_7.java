@Test public void testDecodeRow2string_7() throws Exception {
  String path="test/data/blackbox/rssexpanded-1/7.png";
  String expected="(10)567(11)010101";
  assertCorrectImage2string(path,expected);
}
