@Test public void testSafeFailForMonitorCollectFail(){
  MonitorFilter monitorFilter=new MonitorFilter();
  MonitorFactory mockMonitorFactory=mock(MonitorFactory.class);
  Monitor mockMonitor=mock(Monitor.class);
  Mockito.doThrow(new RuntimeException()).when(mockMonitor).collect(any(URL.class));
  monitorFilter.setMonitorFactory(mockMonitorFactory);
  given(mockMonitorFactory.getMonitor(any(URL.class))).willReturn(mockMonitor);
  Invocation invocation=new RpcInvocation("aaa",new Class<?>[0],new Object[0]);
  monitorFilter.invoke(serviceInvoker,invocation);
}
