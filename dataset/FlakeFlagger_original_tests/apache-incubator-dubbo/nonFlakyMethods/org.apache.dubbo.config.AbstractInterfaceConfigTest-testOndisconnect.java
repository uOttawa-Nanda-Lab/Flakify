@Test public void testOndisconnect() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setOndisconnect("onDisconnect");
  TestCase.assertEquals("onDisconnect",interfaceConfig.getOndisconnect());
}
