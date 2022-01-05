@Test public void testDecodeRow2string_13() throws Exception {
  String expected="(01)90012345678908(3922)795";
  String path="test/data/blackbox/rssexpanded-1/13.png";
  assertCorrectImage2string(path,expected);
}
