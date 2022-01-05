@Test public void testIpV6AddressWithScopeId(){
  URL url=URL.valueOf("2001:0db8:85a3:08d3:1319:8a2e:0370:7344%5/context/path?version=1.0.0&application=morgan");
  assertNull(url.getProtocol());
  assertEquals("2001:0db8:85a3:08d3:1319:8a2e:0370:7344%5",url.getHost());
  assertEquals(0,url.getPort());
  assertEquals("context/path",url.getPath());
  assertEquals(2,url.getParameters().size());
  assertEquals("1.0.0",url.getParameter("version"));
  assertEquals("morgan",url.getParameter("application"));
}
