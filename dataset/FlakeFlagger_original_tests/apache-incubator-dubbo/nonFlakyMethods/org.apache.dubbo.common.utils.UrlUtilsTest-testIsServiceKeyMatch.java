@Test public void testIsServiceKeyMatch() throws Exception {
  URL url=URL.valueOf("test://127.0.0.1");
  URL pattern=url.addParameter(Constants.GROUP_KEY,"test").addParameter(Constants.INTERFACE_KEY,"test").addParameter(Constants.VERSION_KEY,"test");
  URL value=pattern;
  assertTrue(UrlUtils.isServiceKeyMatch(pattern,value));
  pattern=pattern.addParameter(Constants.GROUP_KEY,"*");
  assertTrue(UrlUtils.isServiceKeyMatch(pattern,value));
  pattern=pattern.addParameter(Constants.VERSION_KEY,"*");
  assertTrue(UrlUtils.isServiceKeyMatch(pattern,value));
}
