@Test public void test_addParameter() throws Exception {
  URL url=URL.valueOf("dubbo://admin:hello1234@10.20.130.230:20880/context/path?application=morgan");
  url=url.addParameter("k1","v1");
  assertEquals("dubbo",url.getProtocol());
  assertEquals("admin",url.getUsername());
  assertEquals("hello1234",url.getPassword());
  assertEquals("10.20.130.230",url.getHost());
  assertEquals(20880,url.getPort());
  assertEquals("context/path",url.getPath());
  assertEquals(2,url.getParameters().size());
  assertEquals("morgan",url.getParameter("application"));
  assertEquals("v1",url.getParameter("k1"));
}
