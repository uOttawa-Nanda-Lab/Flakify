@Test(expected=IllegalStateException.class) public void checkStubAndMock8() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setMock(GreetingMock1.class.getName());
  interfaceConfig.checkStubAndMock(Greeting.class);
}
