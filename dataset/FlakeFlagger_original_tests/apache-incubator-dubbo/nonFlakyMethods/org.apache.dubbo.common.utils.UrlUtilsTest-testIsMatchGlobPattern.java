@Test public void testIsMatchGlobPattern() throws Exception {
  assertTrue(UrlUtils.isMatchGlobPattern("*","value"));
  assertTrue(UrlUtils.isMatchGlobPattern("",null));
  assertFalse(UrlUtils.isMatchGlobPattern("","value"));
  assertTrue(UrlUtils.isMatchGlobPattern("value","value"));
  assertTrue(UrlUtils.isMatchGlobPattern("v*","value"));
  assertTrue(UrlUtils.isMatchGlobPattern("*e","value"));
  assertTrue(UrlUtils.isMatchGlobPattern("v*e","value"));
  assertTrue(UrlUtils.isMatchGlobPattern("$key","value",URL.valueOf("dubbo://localhost:8080/Foo?key=v*e")));
}
