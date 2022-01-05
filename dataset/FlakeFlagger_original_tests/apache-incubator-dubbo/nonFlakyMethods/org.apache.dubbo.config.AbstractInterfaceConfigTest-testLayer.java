@Test public void testLayer() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setLayer("layer");
  TestCase.assertEquals("layer",interfaceConfig.getLayer());
}
