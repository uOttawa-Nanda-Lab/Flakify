@Test public void testConnections() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setConnections(1);
  TestCase.assertEquals(1,interfaceConfig.getConnections().intValue());
}
