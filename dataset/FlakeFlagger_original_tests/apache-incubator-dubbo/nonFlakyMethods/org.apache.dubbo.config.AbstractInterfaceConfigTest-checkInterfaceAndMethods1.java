@Test(expected=IllegalStateException.class) public void checkInterfaceAndMethods1() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.checkInterfaceAndMethods(null,null);
}
