@Test public void testMonitorFactory() throws Exception {
  MockMonitorService monitorService=new MockMonitorService();
  URL statistics=new URL("dubbo","10.20.153.10",0).addParameter(MonitorService.APPLICATION,"morgan").addParameter(MonitorService.INTERFACE,"MemberService").addParameter(MonitorService.METHOD,"findPerson").addParameter(MonitorService.CONSUMER,"10.20.153.11").addParameter(MonitorService.SUCCESS,1).addParameter(MonitorService.FAILURE,0).addParameter(MonitorService.ELAPSED,3).addParameter(MonitorService.MAX_ELAPSED,3).addParameter(MonitorService.CONCURRENT,1).addParameter(MonitorService.MAX_CONCURRENT,1);
  Protocol protocol=ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
  ProxyFactory proxyFactory=ExtensionLoader.getExtensionLoader(ProxyFactory.class).getAdaptiveExtension();
  MonitorFactory monitorFactory=ExtensionLoader.getExtensionLoader(MonitorFactory.class).getAdaptiveExtension();
  Exporter<MonitorService> exporter=protocol.export(proxyFactory.getInvoker(monitorService,MonitorService.class,URL.valueOf("dubbo://127.0.0.1:17979/" + MonitorService.class.getName())));
  try {
    Monitor monitor=null;
    long start=System.currentTimeMillis();
    while (System.currentTimeMillis() - start < 60000) {
      monitor=monitorFactory.getMonitor(URL.valueOf("dubbo://127.0.0.1:17979?interval=10"));
      if (monitor == null) {
        continue;
      }
      try {
        monitor.collect(statistics);
        int i=0;
        while (monitorService.getStatistics() == null && i < 200) {
          i++;
          Thread.sleep(10);
        }
        URL result=monitorService.getStatistics();
        Assert.assertEquals(1,result.getParameter(MonitorService.SUCCESS,0));
        Assert.assertEquals(3,result.getParameter(MonitorService.ELAPSED,0));
      }
  finally {
        monitor.destroy();
      }
      break;
    }
    Assert.assertNotNull(monitor);
  }
  finally {
    exporter.unexport();
  }
}
