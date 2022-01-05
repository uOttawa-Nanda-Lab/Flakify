@Test(expected=IllegalStateException.class) public void checkInterfaceAndMethod4() throws Exception {
  MethodConfig methodConfig=new MethodConfig();
  methodConfig.setName("nihao");
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.checkInterfaceAndMethods(Greeting.class,Collections.singletonList(methodConfig));
}
