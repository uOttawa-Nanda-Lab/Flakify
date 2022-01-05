@Test public void test_valueOf_spaceSafe() throws Exception {
  URL url=URL.valueOf("http://1.2.3.4:8080/path?key=value1 value2");
  assertEquals("http://1.2.3.4:8080/path?key=value1 value2",url.toString());
  assertEquals("value1 value2",url.getParameter("key"));
}
