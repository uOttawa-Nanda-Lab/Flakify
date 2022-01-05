@Test public void checkInterfaceAndMethod5() throws Exception {
  MethodConfig methodConfig=new MethodConfig();
  methodConfig.setName("hello");
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.checkInterfaceAndMethods(Greeting.class,Collections.singletonList(methodConfig));
}
