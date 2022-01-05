@Test public void testDecodeRow2string_11() throws Exception {
  String expected="(01)98898765432106(15)991231(3103)001750(10)12A(422)123(21)123456";
  String path="test/data/blackbox/rssexpanded-1/11.png";
  assertCorrectImage2string(path,expected);
}
