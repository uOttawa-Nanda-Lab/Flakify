@Test public void testAvailable(){
  Invoker invoker=mock(Invoker.class);
  MonitorService monitorService=mock(MonitorService.class);
  given(invoker.isAvailable()).willReturn(true);
  given(invoker.getUrl()).willReturn(URL.valueOf("dubbo://127.0.0.1:7070?interval=20"));
  DubboMonitor dubboMonitor=new DubboMonitor(invoker,monitorService);
  assertThat(dubboMonitor.isAvailable(),is(true));
  verify(invoker).isAvailable();
}
