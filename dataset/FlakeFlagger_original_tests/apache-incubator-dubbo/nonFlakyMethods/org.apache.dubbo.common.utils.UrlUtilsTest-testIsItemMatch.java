@Test public void testIsItemMatch() throws Exception {
  assertTrue(UrlUtils.isItemMatch(null,null));
  assertTrue(!UrlUtils.isItemMatch("1",null));
  assertTrue(!UrlUtils.isItemMatch(null,"1"));
  assertTrue(UrlUtils.isItemMatch("1","1"));
  assertTrue(UrlUtils.isItemMatch("*",null));
  assertTrue(UrlUtils.isItemMatch("*","*"));
  assertTrue(UrlUtils.isItemMatch("*","1234"));
  assertTrue(!UrlUtils.isItemMatch(null,"*"));
}
