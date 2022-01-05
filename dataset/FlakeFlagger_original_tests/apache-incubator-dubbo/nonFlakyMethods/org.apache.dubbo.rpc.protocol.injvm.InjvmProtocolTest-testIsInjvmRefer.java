@Test public void testIsInjvmRefer() throws Exception {
  DemoService service=new DemoServiceImpl();
  URL url=URL.valueOf("injvm://127.0.0.1/TestService").addParameter(Constants.INTERFACE_KEY,DemoService.class.getName());
  Exporter<?> exporter=protocol.export(proxy.getInvoker(service,DemoService.class,url));
  exporters.add(exporter);
  url=url.setProtocol("dubbo");
  assertTrue(InjvmProtocol.getInjvmProtocol().isInjvmRefer(url));
  url=url.addParameter(Constants.GROUP_KEY,"*").addParameter(Constants.VERSION_KEY,"*");
  assertTrue(InjvmProtocol.getInjvmProtocol().isInjvmRefer(url));
  url=URL.valueOf("fake://127.0.0.1/TestService").addParameter(Constants.SCOPE_KEY,Constants.SCOPE_LOCAL);
  assertTrue(InjvmProtocol.getInjvmProtocol().isInjvmRefer(url));
  url=URL.valueOf("fake://127.0.0.1/TestService").addParameter(Constants.LOCAL_PROTOCOL,true);
  assertTrue(InjvmProtocol.getInjvmProtocol().isInjvmRefer(url));
  url=URL.valueOf("fake://127.0.0.1/TestService").addParameter(Constants.SCOPE_KEY,Constants.SCOPE_REMOTE);
  assertFalse(InjvmProtocol.getInjvmProtocol().isInjvmRefer(url));
  url=URL.valueOf("fake://127.0.0.1/TestService").addParameter(Constants.GENERIC_KEY,true);
  assertFalse(InjvmProtocol.getInjvmProtocol().isInjvmRefer(url));
}
