@Test public void testOnconnect() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setOnconnect("onConnect");
  TestCase.assertEquals("onConnect",interfaceConfig.getOnconnect());
}
