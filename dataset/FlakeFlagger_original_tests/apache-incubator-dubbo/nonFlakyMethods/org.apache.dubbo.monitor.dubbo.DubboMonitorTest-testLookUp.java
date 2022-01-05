@Test public void testLookUp(){
  Invoker invoker=mock(Invoker.class);
  MonitorService monitorService=mock(MonitorService.class);
  URL queryUrl=URL.valueOf("dubbo://127.0.0.1:7070?interval=20");
  given(invoker.getUrl()).willReturn(queryUrl);
  DubboMonitor dubboMonitor=new DubboMonitor(invoker,monitorService);
  dubboMonitor.lookup(queryUrl);
  verify(monitorService).lookup(eq(queryUrl));
}
