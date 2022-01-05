@Test public void testOwner() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setOwner("owner");
  TestCase.assertEquals("owner",interfaceConfig.getOwner());
}
