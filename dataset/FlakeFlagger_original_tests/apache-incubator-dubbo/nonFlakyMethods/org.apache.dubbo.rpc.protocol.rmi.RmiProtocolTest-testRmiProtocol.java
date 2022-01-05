@Test public void testRmiProtocol() throws Exception {
{
    DemoService service=new DemoServiceImpl();
    Exporter<?> rpcExporter=protocol.export(proxy.getInvoker(service,DemoService.class,URL.valueOf("rmi://127.0.0.1:9001/TestService")));
    service=proxy.getProxy(protocol.refer(DemoService.class,URL.valueOf("rmi://127.0.0.1:9001/TestService")));
    assertEquals(service.getSize(null),-1);
    assertEquals(service.getSize(new String[]{"","",""}),3);
    Object result=service.invoke("rmi://127.0.0.1:9001/TestService","invoke");
    assertEquals("rmi://127.0.0.1:9001/TestService:invoke",result);
    rpcExporter.unexport();
  }
{
    RemoteService remoteService=new RemoteServiceImpl();
    Exporter<?> rpcExporter=protocol.export(proxy.getInvoker(remoteService,RemoteService.class,URL.valueOf("rmi://127.0.0.1:9002/remoteService")));
    remoteService=proxy.getProxy(protocol.refer(RemoteService.class,URL.valueOf("rmi://127.0.0.1:9002/remoteService")));
    remoteService.getThreadName();
    for (int i=0; i < 100; i++) {
      String say=remoteService.sayHello("abcd");
      assertEquals("hello abcd@" + RemoteServiceImpl.class.getName(),say);
    }
    rpcExporter.unexport();
  }
}
