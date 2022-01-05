@Test public void test_destroyWontCloseAllProtocol() throws Exception {
  Protocol autowireProtocol=ExtensionLoader.getExtensionLoader(Protocol.class).getAdaptiveExtension();
  Protocol InjvmProtocol=ExtensionLoader.getExtensionLoader(Protocol.class).getExtension("injvm");
  assertEquals(0,InjvmProtocol.getDefaultPort());
  InjvmProtocol.export(invoker);
  Invoker<IEcho> refer=InjvmProtocol.refer(IEcho.class,url);
  IEcho echoProxy=proxyFactory.getProxy(refer);
  assertEquals("ok",echoProxy.echo("ok"));
  try {
    autowireProtocol.destroy();
  }
 catch (  UnsupportedOperationException expected) {
    assertThat(expected.getMessage(),containsString("of interface org.apache.dubbo.rpc.Protocol is not adaptive method!"));
  }
  assertEquals("ok2",echoProxy.echo("ok2"));
}
