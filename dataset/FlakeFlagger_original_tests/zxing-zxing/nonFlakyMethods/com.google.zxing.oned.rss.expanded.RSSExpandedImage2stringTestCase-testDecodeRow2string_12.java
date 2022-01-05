@Test public void testDecodeRow2string_12() throws Exception {
  String expected="(01)98898765432106(3103)001750";
  String path="test/data/blackbox/rssexpanded-1/12.png";
  assertCorrectImage2string(path,expected);
}
