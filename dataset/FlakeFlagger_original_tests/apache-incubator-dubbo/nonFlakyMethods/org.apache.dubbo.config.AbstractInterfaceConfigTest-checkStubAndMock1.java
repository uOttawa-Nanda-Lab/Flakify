@Test(expected=IllegalStateException.class) public void checkStubAndMock1() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setLocal(GreetingLocal1.class.getName());
  interfaceConfig.checkStubAndMock(Greeting.class);
}
