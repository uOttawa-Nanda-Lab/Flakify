@Test public void testRmiProtocolTimeout() throws Exception {
  System.setProperty("sun.rmi.transport.tcp.responseTimeout","1000");
  DemoService service=new DemoServiceImpl();
  Exporter<?> rpcExporter=protocol.export(proxy.getInvoker(service,DemoService.class,URL.valueOf("rmi://127.0.0.1:9001/TestService")));
  service=proxy.getProxy(protocol.refer(DemoService.class,URL.valueOf("rmi://127.0.0.1:9001/TestService")));
  try {
    try {
      service.throwTimeout();
    }
 catch (    RpcException e) {
      assertEquals(true,e.isTimeout());
      assertEquals(true,e.getMessage().contains("Read timed out"));
    }
  }
  finally {
    rpcExporter.unexport();
  }
}
