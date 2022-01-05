@Test(expected=IllegalStateException.class) public void checkStubAndMock9() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setMock(GreetingMock2.class.getName());
  interfaceConfig.checkStubAndMock(Greeting.class);
}
