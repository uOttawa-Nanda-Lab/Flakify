@Test public void testParseUrl(){
  String address="remote://root:alibaba@127.0.0.1:9090/dubbo.test.api";
  URL url=UrlUtils.parseURL(address,null);
  assertEquals(localAddress + ":9090",url.getAddress());
  assertEquals("root",url.getUsername());
  assertEquals("alibaba",url.getPassword());
  assertEquals("dubbo.test.api",url.getPath());
  assertEquals(9090,url.getPort());
  assertEquals("remote",url.getProtocol());
}
