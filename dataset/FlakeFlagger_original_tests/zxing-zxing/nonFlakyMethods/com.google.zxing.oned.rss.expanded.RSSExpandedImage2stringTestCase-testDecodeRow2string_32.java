@Test public void testDecodeRow2string_32() throws Exception {
  String expected="(15)991231(3103)001750(10)12A(422)123(21)123456(423)0123456789012";
  String path="test/data/blackbox/rssexpanded-1/32.png";
  assertCorrectImage2string(path,expected);
}
