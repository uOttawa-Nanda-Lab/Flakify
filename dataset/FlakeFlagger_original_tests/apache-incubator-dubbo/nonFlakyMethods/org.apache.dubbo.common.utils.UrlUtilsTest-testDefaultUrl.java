@Test public void testDefaultUrl(){
  String address="127.0.0.1";
  URL url=UrlUtils.parseURL(address,null);
  assertEquals(localAddress + ":9090",url.getAddress());
  assertEquals(9090,url.getPort());
  assertEquals("dubbo",url.getProtocol());
  assertNull(url.getUsername());
  assertNull(url.getPassword());
  assertNull(url.getPath());
}
