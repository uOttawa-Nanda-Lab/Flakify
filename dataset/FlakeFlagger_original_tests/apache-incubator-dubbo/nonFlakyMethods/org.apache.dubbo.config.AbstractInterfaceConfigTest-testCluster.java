@Test public void testCluster() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setCluster("mockcluster");
  TestCase.assertEquals("mockcluster",interfaceConfig.getCluster());
}
