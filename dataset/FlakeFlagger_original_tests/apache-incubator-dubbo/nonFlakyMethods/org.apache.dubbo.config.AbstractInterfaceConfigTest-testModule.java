@Test public void testModule() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  ModuleConfig moduleConfig=new ModuleConfig();
  interfaceConfig.setModule(moduleConfig);
  TestCase.assertSame(moduleConfig,interfaceConfig.getModule());
}
