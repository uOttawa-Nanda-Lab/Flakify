@Test public void testMonitor() throws Exception {
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  interfaceConfig.setMonitor("monitor-addr");
  TestCase.assertEquals("monitor-addr",interfaceConfig.getMonitor().getAddress());
  MonitorConfig monitorConfig=new MonitorConfig();
  interfaceConfig.setMonitor(monitorConfig);
  TestCase.assertSame(monitorConfig,interfaceConfig.getMonitor());
}
