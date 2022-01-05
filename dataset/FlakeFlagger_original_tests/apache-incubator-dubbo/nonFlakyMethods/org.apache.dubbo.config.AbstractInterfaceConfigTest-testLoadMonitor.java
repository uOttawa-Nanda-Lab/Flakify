@Test public void testLoadMonitor() throws Exception {
  System.setProperty("dubbo.monitor.address","monitor-addr:12080");
  System.setProperty("dubbo.monitor.protocol","monitor");
  InterfaceConfig interfaceConfig=new InterfaceConfig();
  URL url=interfaceConfig.loadMonitor(new URL("dubbo","addr1",9090));
  TestCase.assertEquals("monitor-addr:12080",url.getAddress());
  TestCase.assertEquals(MonitorService.class.getName(),url.getParameter("interface"));
  TestCase.assertNotNull(url.getParameter("dubbo"));
  TestCase.assertNotNull(url.getParameter("pid"));
  TestCase.assertNotNull(url.getParameter("timestamp"));
}
