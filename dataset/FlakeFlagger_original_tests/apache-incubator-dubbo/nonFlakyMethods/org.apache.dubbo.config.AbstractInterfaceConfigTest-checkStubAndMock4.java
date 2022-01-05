@Test(expected=IllegalStateException.class) public void checkStubAndMock4() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setStub(GreetingLocal1.class.getName());
  interfaceConfig.checkStubAndMock(Greeting.class);
}
