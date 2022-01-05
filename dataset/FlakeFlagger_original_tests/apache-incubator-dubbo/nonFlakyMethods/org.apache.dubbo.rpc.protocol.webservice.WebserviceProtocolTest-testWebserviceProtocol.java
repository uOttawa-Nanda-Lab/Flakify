@Test public void testWebserviceProtocol() throws Exception {
  DemoService service=new DemoServiceImpl();
  protocol.export(proxy.getInvoker(service,DemoService.class,URL.valueOf("webservice://127.0.0.1:9019/" + DemoService.class.getName())));
  service=proxy.getProxy(protocol.refer(DemoService.class,URL.valueOf("webservice://127.0.0.1:9019/" + DemoService.class.getName() + "?timeout=3000")));
  assertEquals(service.create(1,"kk").getName(),"kk");
  assertEquals(service.getSize(null),-1);
  assertEquals(service.getSize(new String[]{"","",""}),3);
  Object object=service.invoke("webservice://127.0.0.1:9019/" + DemoService.class.getName() + "","invoke");
  System.out.println(object);
  assertEquals("webservice://127.0.0.1:9019/org.apache.dubbo.rpc.protocol.webservice.DemoService:invoke",object);
  StringBuffer buf=new StringBuffer();
  for (int i=0; i < 1024 * 32 + 32; i++)   buf.append('A');
  assertEquals(32800,service.stringLength(buf.toString()));
}
