@Test public void testSum(){
  URL statistics=new URL("dubbo","10.20.153.11",0).addParameter(MonitorService.APPLICATION,"morgan").addParameter(MonitorService.INTERFACE,"MemberService").addParameter(MonitorService.METHOD,"findPerson").addParameter(MonitorService.CONSUMER,"10.20.153.11").addParameter(MonitorService.SUCCESS,1).addParameter(MonitorService.FAILURE,0).addParameter(MonitorService.ELAPSED,3).addParameter(MonitorService.MAX_ELAPSED,3).addParameter(MonitorService.CONCURRENT,1).addParameter(MonitorService.MAX_CONCURRENT,1);
  Invoker invoker=mock(Invoker.class);
  MonitorService monitorService=mock(MonitorService.class);
  given(invoker.getUrl()).willReturn(URL.valueOf("dubbo://127.0.0.1:7070?interval=20"));
  DubboMonitor dubboMonitor=new DubboMonitor(invoker,monitorService);
  dubboMonitor.collect(statistics);
  dubboMonitor.collect(statistics.addParameter(MonitorService.SUCCESS,3).addParameter(MonitorService.CONCURRENT,2).addParameter(MonitorService.INPUT,1).addParameter(MonitorService.OUTPUT,2));
  dubboMonitor.collect(statistics.addParameter(MonitorService.SUCCESS,6).addParameter(MonitorService.ELAPSED,2));
  dubboMonitor.send();
  ArgumentCaptor<URL> summaryCaptor=ArgumentCaptor.forClass(URL.class);
  verify(monitorService,atLeastOnce()).collect(summaryCaptor.capture());
  List<URL> allValues=summaryCaptor.getAllValues();
  assertThat(allValues,not(nullValue()));
  assertThat(allValues,hasItem(new CustomMatcher<URL>("Monitor count should greater than 1"){
    @Override public boolean matches(    Object item){
      URL url=(URL)item;
      return Integer.valueOf(url.getParameter(MonitorService.SUCCESS)) > 1;
    }
  }
));
}
