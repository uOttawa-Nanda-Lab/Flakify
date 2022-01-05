@Test(expected=IllegalStateException.class) public void checkApplication2() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.checkApplication();
}
