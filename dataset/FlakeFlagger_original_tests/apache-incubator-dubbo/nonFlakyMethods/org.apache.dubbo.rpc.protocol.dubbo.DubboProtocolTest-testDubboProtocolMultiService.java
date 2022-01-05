@Test public void testDubboProtocolMultiService() throws Exception {
  DemoService service=new DemoServiceImpl();
  protocol.export(proxy.getInvoker(service,DemoService.class,URL.valueOf("dubbo://127.0.0.1:9010/" + DemoService.class.getName())));
  service=proxy.getProxy(protocol.refer(DemoService.class,URL.valueOf("dubbo://127.0.0.1:9010/" + DemoService.class.getName())));
  RemoteService remote=new RemoteServiceImpl();
  protocol.export(proxy.getInvoker(remote,RemoteService.class,URL.valueOf("dubbo://127.0.0.1:9010/" + RemoteService.class.getName())));
  remote=proxy.getProxy(protocol.refer(RemoteService.class,URL.valueOf("dubbo://127.0.0.1:9010/" + RemoteService.class.getName())));
  service.sayHello("world");
  assertEquals("world",service.echo("world"));
  assertEquals("hello world@" + RemoteServiceImpl.class.getName(),remote.sayHello("world"));
  EchoService serviceEcho=(EchoService)service;
  assertEquals(serviceEcho.$echo("test"),"test");
  EchoService remoteEecho=(EchoService)remote;
  assertEquals(remoteEecho.$echo("ok"),"ok");
}
