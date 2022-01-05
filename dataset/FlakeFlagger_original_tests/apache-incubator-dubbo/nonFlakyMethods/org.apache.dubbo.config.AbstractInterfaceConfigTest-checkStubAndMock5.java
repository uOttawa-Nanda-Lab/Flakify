@Test(expected=IllegalStateException.class) public void checkStubAndMock5() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setStub(GreetingLocal2.class.getName());
  interfaceConfig.checkStubAndMock(Greeting.class);
}
