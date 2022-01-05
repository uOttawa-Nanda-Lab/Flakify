@Test public void testDecodeRow2string_31() throws Exception {
  String expected="(10)1098,1234";
  String path="test/data/blackbox/rssexpanded-1/31.png";
  assertCorrectImage2string(path,expected);
}
