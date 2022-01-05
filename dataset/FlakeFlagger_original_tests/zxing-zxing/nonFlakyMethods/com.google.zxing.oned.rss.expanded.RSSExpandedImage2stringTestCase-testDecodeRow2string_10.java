@Test public void testDecodeRow2string_10() throws Exception {
  String path="test/data/blackbox/rssexpanded-1/10.png";
  String expected="(01)98898765432106(15)991231(3103)001750(10)12A(422)123(21)123456(423)012345678901";
  assertCorrectImage2string(path,expected);
}
