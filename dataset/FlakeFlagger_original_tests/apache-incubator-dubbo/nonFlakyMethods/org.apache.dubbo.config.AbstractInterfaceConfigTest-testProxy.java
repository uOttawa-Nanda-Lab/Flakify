@Test public void testProxy() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setProxy("mockproxyfactory");
  TestCase.assertEquals("mockproxyfactory",interfaceConfig.getProxy());
}
