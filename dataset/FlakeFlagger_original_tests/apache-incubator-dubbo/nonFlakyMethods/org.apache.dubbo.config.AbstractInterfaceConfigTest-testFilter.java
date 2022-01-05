@Test public void testFilter() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setFilter("mockfilter");
  TestCase.assertEquals("mockfilter",interfaceConfig.getFilter());
}
