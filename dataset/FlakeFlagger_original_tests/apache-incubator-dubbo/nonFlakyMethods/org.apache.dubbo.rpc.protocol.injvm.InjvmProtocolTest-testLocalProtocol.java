@Test public void testLocalProtocol() throws Exception {
  DemoService service=new DemoServiceImpl();
  Invoker<?> invoker=proxy.getInvoker(service,DemoService.class,URL.valueOf("injvm://127.0.0.1/TestService").addParameter(Constants.INTERFACE_KEY,DemoService.class.getName()));
  assertTrue(invoker.isAvailable());
  Exporter<?> exporter=protocol.export(invoker);
  exporters.add(exporter);
  service=proxy.getProxy(protocol.refer(DemoService.class,URL.valueOf("injvm://127.0.0.1/TestService").addParameter(Constants.INTERFACE_KEY,DemoService.class.getName())));
  assertEquals(service.getSize(new String[]{"","",""}),3);
  service.invoke("injvm://127.0.0.1/TestService","invoke");
  InjvmInvoker injvmInvoker=new InjvmInvoker(DemoService.class,URL.valueOf("injvm://127.0.0.1/TestService"),null,new HashMap<String,Exporter<?>>());
  assertFalse(injvmInvoker.isAvailable());
}
