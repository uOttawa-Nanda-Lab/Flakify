@Test public void testMonitorFactoryIpCache() throws Exception {
  URL url=URL.valueOf("dubbo://" + NetUtils.getLocalAddress().getHostName() + ":2233");
  Monitor monitor1=monitorFactory.getMonitor(url);
  Monitor monitor2=monitorFactory.getMonitor(url);
  if (monitor1 == null || monitor2 == null) {
    Thread.sleep(2000);
    monitor1=monitorFactory.getMonitor(url);
    monitor2=monitorFactory.getMonitor(url);
  }
  Assert.assertEquals(monitor1,monitor2);
}
