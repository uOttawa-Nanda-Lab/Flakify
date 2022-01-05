@Test public void testMonitorFactoryGroupCache() throws Exception {
  URL url1=URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":2233?group=aaa");
  URL url2=URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":2233?group=bbb");
  Monitor monitor1=monitorFactory.getMonitor(url1);
  Monitor monitor2=monitorFactory.getMonitor(url2);
  if (monitor1 == null || monitor2 == null) {
    Thread.sleep(2000);
    monitor1=monitorFactory.getMonitor(url1);
    monitor2=monitorFactory.getMonitor(url2);
  }
  Assert.assertNotSame(monitor1,monitor2);
}
