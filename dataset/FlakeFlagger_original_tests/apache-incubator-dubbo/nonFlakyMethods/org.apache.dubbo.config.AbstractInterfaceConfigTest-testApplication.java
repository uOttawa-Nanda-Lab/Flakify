@Test public void testApplication() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  ApplicationConfig applicationConfig=new ApplicationConfig();
  interfaceConfig.setApplication(applicationConfig);
  TestCase.assertSame(applicationConfig,interfaceConfig.getApplication());
}
