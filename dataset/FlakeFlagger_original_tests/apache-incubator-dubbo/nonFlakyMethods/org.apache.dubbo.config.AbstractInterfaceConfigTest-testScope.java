@Test public void testScope() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setScope("scope");
  TestCase.assertEquals("scope",interfaceConfig.getScope());
}
