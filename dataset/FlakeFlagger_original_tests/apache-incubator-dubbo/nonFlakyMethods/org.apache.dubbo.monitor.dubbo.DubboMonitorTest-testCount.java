@Test public void testCount() throws Exception {
  DubboMonitor monitor=new DubboMonitor(monitorInvoker,monitorService);
  URL statistics=new URL("dubbo","10.20.153.10",0).addParameter(MonitorService.APPLICATION,"morgan").addParameter(MonitorService.INTERFACE,"MemberService").addParameter(MonitorService.METHOD,"findPerson").addParameter(MonitorService.CONSUMER,"10.20.153.11").addParameter(MonitorService.SUCCESS,1).addParameter(MonitorService.FAILURE,0).addParameter(MonitorService.ELAPSED,3).addParameter(MonitorService.MAX_ELAPSED,3).addParameter(MonitorService.CONCURRENT,1).addParameter(MonitorService.MAX_CONCURRENT,1);
  monitor.collect(statistics);
  while (lastStatistics == null) {
    Thread.sleep(10);
  }
  Assert.assertEquals(lastStatistics.getParameter(MonitorService.APPLICATION),"morgan");
  Assert.assertEquals(lastStatistics.getProtocol(),"dubbo");
  Assert.assertEquals(lastStatistics.getHost(),"10.20.153.10");
  Assert.assertEquals(lastStatistics.getParameter(MonitorService.APPLICATION),"morgan");
  Assert.assertEquals(lastStatistics.getParameter(MonitorService.INTERFACE),"MemberService");
  Assert.assertEquals(lastStatistics.getParameter(MonitorService.METHOD),"findPerson");
  Assert.assertEquals(lastStatistics.getParameter(MonitorService.CONSUMER),"10.20.153.11");
  Assert.assertEquals(lastStatistics.getParameter(MonitorService.SUCCESS),"1");
  Assert.assertEquals(lastStatistics.getParameter(MonitorService.FAILURE),"0");
  Assert.assertEquals(lastStatistics.getParameter(MonitorService.ELAPSED),"3");
  Assert.assertEquals(lastStatistics.getParameter(MonitorService.MAX_ELAPSED),"3");
  Assert.assertEquals(lastStatistics.getParameter(MonitorService.CONCURRENT),"1");
  Assert.assertEquals(lastStatistics.getParameter(MonitorService.MAX_CONCURRENT),"1");
  monitor.destroy();
}
