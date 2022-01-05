@Test public void testCheckRegistry1() throws Exception {
  System.setProperty("dubbo.registry.address","addr1|addr2");
  try {
    InterfaceConfig interfaceConfig=new InterfaceConfig();
    interfaceConfig.checkRegistry();
    TestCase.assertEquals(2,interfaceConfig.getRegistries().size());
  }
  finally {
    System.clearProperty("dubbo.registry.address");
  }
}
