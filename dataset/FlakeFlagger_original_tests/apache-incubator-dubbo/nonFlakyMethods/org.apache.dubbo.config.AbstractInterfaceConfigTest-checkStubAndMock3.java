@Test public void checkStubAndMock3() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setLocal(GreetingLocal3.class.getName());
  interfaceConfig.checkStubAndMock(Greeting.class);
}
