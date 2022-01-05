@Test public void testDecodeRow2string_4() throws Exception {
  String path="test/data/blackbox/rssexpanded-1/4.png";
  String expected="(01)98898765432106(3202)012345(15)991231";
  assertCorrectImage2string(path,expected);
}
