@Test public void testCallbacks() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setCallbacks(2);
  TestCase.assertEquals(2,interfaceConfig.getCallbacks().intValue());
}
