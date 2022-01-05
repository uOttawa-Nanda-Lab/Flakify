@Test public void testRegistry() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  RegistryConfig registryConfig=new RegistryConfig();
  interfaceConfig.setRegistry(registryConfig);
  TestCase.assertSame(registryConfig,interfaceConfig.getRegistry());
}
