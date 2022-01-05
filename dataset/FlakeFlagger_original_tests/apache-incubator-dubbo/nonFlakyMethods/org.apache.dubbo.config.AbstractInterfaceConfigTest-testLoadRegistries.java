@Test public void testLoadRegistries() throws Exception {
  System.setProperty("dubbo.registry.address","addr1");
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  List<URL> urls=interfaceConfig.loadRegistries(true);
  TestCase.assertEquals(1,urls.size());
  URL url=urls.get(0);
  TestCase.assertEquals("registry",url.getProtocol());
  TestCase.assertEquals("addr1:9090",url.getAddress());
  TestCase.assertEquals(RegistryService.class.getName(),url.getPath());
  TestCase.assertTrue(url.getParameters().containsKey("timestamp"));
  TestCase.assertTrue(url.getParameters().containsKey("pid"));
  TestCase.assertTrue(url.getParameters().containsKey("registry"));
  TestCase.assertTrue(url.getParameters().containsKey("dubbo"));
}
