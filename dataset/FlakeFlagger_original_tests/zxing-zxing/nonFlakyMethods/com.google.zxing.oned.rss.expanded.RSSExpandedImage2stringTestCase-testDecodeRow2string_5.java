@Test public void testDecodeRow2string_5() throws Exception {
  String path="test/data/blackbox/rssexpanded-1/5.png";
  String expected="(01)90614141000015(3202)000150";
  assertCorrectImage2string(path,expected);
}
