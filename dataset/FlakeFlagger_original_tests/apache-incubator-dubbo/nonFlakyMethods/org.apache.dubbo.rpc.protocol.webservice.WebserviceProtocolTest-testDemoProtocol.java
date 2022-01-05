@Test public void testDemoProtocol() throws Exception {
  DemoService service=new DemoServiceImpl();
  protocol.export(proxy.getInvoker(service,DemoService.class,URL.valueOf("webservice://127.0.0.1:9019/" + DemoService.class.getName() + "?codec=exchange")));
  service=proxy.getProxy(protocol.refer(DemoService.class,URL.valueOf("webservice://127.0.0.1:9019/" + DemoService.class.getName() + "?codec=exchange&timeout=3000")));
  assertEquals(service.getSize(new String[]{"","",""}),3);
}
