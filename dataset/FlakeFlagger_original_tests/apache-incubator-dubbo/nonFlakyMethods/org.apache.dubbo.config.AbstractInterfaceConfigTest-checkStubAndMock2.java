@Test(expected=IllegalStateException.class) public void checkStubAndMock2() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setLocal(GreetingLocal2.class.getName());
  interfaceConfig.checkStubAndMock(Greeting.class);
}
