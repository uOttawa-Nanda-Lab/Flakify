@Test public void testListener() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setListener("mockinvokerlistener");
  TestCase.assertEquals("mockinvokerlistener",interfaceConfig.getListener());
}
