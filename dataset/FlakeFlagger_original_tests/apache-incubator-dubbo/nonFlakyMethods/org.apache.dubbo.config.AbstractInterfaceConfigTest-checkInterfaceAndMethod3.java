@Test(expected=IllegalStateException.class) public void checkInterfaceAndMethod3() throws Exception {
  MethodConfig methodConfig=new MethodConfig();
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.checkInterfaceAndMethods(Greeting.class,Collections.singletonList(methodConfig));
}
