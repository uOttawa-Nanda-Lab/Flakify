@Test(expected=IllegalStateException.class) public void checkStubAndMock7() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setMock("return {a, b}");
  interfaceConfig.checkStubAndMock(Greeting.class);
}
