@Test public void testDecodeRow2string_26() throws Exception {
  String expected="(10)5678(11)010101";
  String path="test/data/blackbox/rssexpanded-1/26.png";
  assertCorrectImage2string(path,expected);
}
