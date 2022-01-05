@Test(expected=IllegalStateException.class) public void checkInterfaceAndMethods2() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.checkInterfaceAndMethods(AbstractInterfaceConfigTest.class,null);
}
