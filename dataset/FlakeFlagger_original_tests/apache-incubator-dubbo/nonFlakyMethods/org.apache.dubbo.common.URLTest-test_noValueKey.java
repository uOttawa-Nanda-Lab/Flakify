@Test public void test_noValueKey() throws Exception {
  URL url=URL.valueOf("http://1.2.3.4:8080/path?k0&k1=v1");
  assertTrue(url.hasParameter("k0"));
  assertEquals("k0",url.getParameter("k0"));
}
