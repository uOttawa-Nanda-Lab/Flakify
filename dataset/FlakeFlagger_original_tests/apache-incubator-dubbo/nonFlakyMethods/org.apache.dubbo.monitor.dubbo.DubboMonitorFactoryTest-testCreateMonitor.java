@Test public void testCreateMonitor(){
  URL urlWithoutPath=URL.valueOf("http://10.10.10.11");
  Monitor monitor=dubboMonitorFactory.createMonitor(urlWithoutPath);
  assertThat(monitor,not(nullValue()));
  URL urlWithFilterKey=URL.valueOf("http://10.10.10.11/").addParameter(Constants.REFERENCE_FILTER_KEY,"testFilter");
  monitor=dubboMonitorFactory.createMonitor(urlWithFilterKey);
  assertThat(monitor,not(nullValue()));
  ArgumentCaptor<Invoker> invokerArgumentCaptor=ArgumentCaptor.forClass(Invoker.class);
  verify(proxyFactory,atLeastOnce()).getProxy(invokerArgumentCaptor.capture());
  Invoker invoker=invokerArgumentCaptor.getValue();
  assertThat(invoker.getUrl().getParameter(Constants.REFERENCE_FILTER_KEY),containsString("testFilter"));
}
