@Test public void testNotifyOverride() throws Exception {
  URL newRegistryUrl=registryUrl.addParameter(Constants.EXPORT_KEY,serviceUrl);
  Invoker<RegistryProtocolTest> invoker=new MockInvoker<RegistryProtocolTest>(RegistryProtocolTest.class,newRegistryUrl);
  Exporter<?> exporter=protocol.export(invoker);
  RegistryProtocol rprotocol=RegistryProtocol.getRegistryProtocol();
  NotifyListener listener=getListener(rprotocol);
  List<URL> urls=new ArrayList<URL>();
  urls.add(URL.valueOf("override://0.0.0.0/?timeout=1000"));
  urls.add(URL.valueOf("override://0.0.0.0/" + service + "?timeout=100"));
  urls.add(URL.valueOf("override://0.0.0.0/" + service + "?x=y"));
  listener.notify(urls);
  assertEquals(true,exporter.getInvoker().isAvailable());
  assertEquals("100",exporter.getInvoker().getUrl().getParameter("timeout"));
  assertEquals("y",exporter.getInvoker().getUrl().getParameter("x"));
  exporter.unexport();
  destroyRegistryProtocol();
}
