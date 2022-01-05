@Test public void testLocal() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setLocal((Boolean)null);
  TestCase.assertNull(interfaceConfig.getLocal());
  interfaceConfig.setLocal(true);
  TestCase.assertEquals("true",interfaceConfig.getLocal());
  interfaceConfig.setLocal("GreetingMock");
  TestCase.assertEquals("GreetingMock",interfaceConfig.getLocal());
}
