@Test public void testRegistries() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  RegistryConfig registryConfig=new RegistryConfig();
  interfaceConfig.setRegistries(Collections.singletonList(registryConfig));
  TestCase.assertEquals(1,interfaceConfig.getRegistries().size());
  TestCase.assertSame(registryConfig,interfaceConfig.getRegistries().get(0));
}
