@Test(expected=IllegalStateException.class) public void testCheckRegistry2() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.checkRegistry();
}
