@Test public void testSkipMonitorIfNotHasKey(){
  MonitorFilter monitorFilter=new MonitorFilter();
  MonitorFactory mockMonitorFactory=mock(MonitorFactory.class);
  monitorFilter.setMonitorFactory(mockMonitorFactory);
  Invocation invocation=new RpcInvocation("aaa",new Class<?>[0],new Object[0]);
  Invoker invoker=mock(Invoker.class);
  given(invoker.getUrl()).willReturn(URL.valueOf("dubbo://" + NetUtils.getLocalHost() + ":20880?"+ Constants.APPLICATION_KEY+ "=abc&"+ Constants.SIDE_KEY+ "="+ Constants.CONSUMER_SIDE));
  monitorFilter.invoke(invoker,invocation);
  verify(mockMonitorFactory,never()).getMonitor(any(URL.class));
}
