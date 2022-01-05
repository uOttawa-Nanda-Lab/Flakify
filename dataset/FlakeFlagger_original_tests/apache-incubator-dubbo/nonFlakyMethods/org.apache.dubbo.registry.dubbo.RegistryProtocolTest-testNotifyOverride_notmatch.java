/** 
 * The name of the service does not match and can't override invoker Service name matching, service version number mismatch
 */
@Test public void testNotifyOverride_notmatch() throws Exception {
  URL newRegistryUrl=registryUrl.addParameter(Constants.EXPORT_KEY,serviceUrl);
  Invoker<RegistryProtocolTest> invoker=new MockInvoker<RegistryProtocolTest>(RegistryProtocolTest.class,newRegistryUrl);
  Exporter<?> exporter=protocol.export(invoker);
  RegistryProtocol rprotocol=RegistryProtocol.getRegistryProtocol();
  NotifyListener listener=getListener(rprotocol);
  List<URL> urls=new ArrayList<URL>();
  urls.add(URL.valueOf("override://0.0.0.0/org.apache.dubbo.registry.protocol.HackService?timeout=100"));
  listener.notify(urls);
  assertEquals(true,exporter.getInvoker().isAvailable());
  assertEquals(null,exporter.getInvoker().getUrl().getParameter("timeout"));
  exporter.unexport();
  destroyRegistryProtocol();
}
