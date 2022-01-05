@Test public void test_valueOf_noProtocolAndHost() throws Exception {
  URL url=URL.valueOf("/context/path?version=1.0.0&application=morgan");
  assertNull(url.getProtocol());
  assertNull(url.getUsername());
  assertNull(url.getPassword());
  assertNull(url.getHost());
  assertEquals(0,url.getPort());
  assertEquals("context/path",url.getPath());
  assertEquals(2,url.getParameters().size());
  assertEquals("1.0.0",url.getParameter("version"));
  assertEquals("morgan",url.getParameter("application"));
  url=URL.valueOf("context/path?version=1.0.0&application=morgan");
  assertNull(url.getProtocol());
  assertNull(url.getUsername());
  assertNull(url.getPassword());
  assertEquals("context",url.getHost());
  assertEquals(0,url.getPort());
  assertEquals("path",url.getPath());
  assertEquals(2,url.getParameters().size());
  assertEquals("1.0.0",url.getParameter("version"));
  assertEquals("morgan",url.getParameter("application"));
}
