@Test public void checkStubAndMock6() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setStub(GreetingLocal3.class.getName());
  interfaceConfig.checkStubAndMock(Greeting.class);
}
