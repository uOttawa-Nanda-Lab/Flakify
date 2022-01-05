@Test public void testIpV6Address(){
  URL url=URL.valueOf("ad@min111:haha@1234@2001:0db8:85a3:08d3:1319:8a2e:0370:7344:20880/context/path?version=1.0.0&application=morgan");
  assertNull(url.getProtocol());
  assertEquals("ad@min111",url.getUsername());
  assertEquals("haha@1234",url.getPassword());
  assertEquals("2001:0db8:85a3:08d3:1319:8a2e:0370:7344",url.getHost());
  assertEquals(20880,url.getPort());
  assertEquals("context/path",url.getPath());
  assertEquals(2,url.getParameters().size());
  assertEquals("1.0.0",url.getParameter("version"));
  assertEquals("morgan",url.getParameter("application"));
}
