@Test public void testStub() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setStub((Boolean)null);
  TestCase.assertNull(interfaceConfig.getStub());
  interfaceConfig.setStub(true);
  TestCase.assertEquals("true",interfaceConfig.getStub());
  interfaceConfig.setStub("GreetingMock");
  TestCase.assertEquals("GreetingMock",interfaceConfig.getStub());
}
