@Test public void testExecute() throws Exception {
  ProviderModel providerModel=mock(ProviderModel.class);
  when(providerModel.getServiceName()).thenReturn("org.apache.dubbo.BarService");
  ApplicationModel.initProviderModel("org.apache.dubbo.BarService",providerModel);
  Invoker providerInvoker=mock(Invoker.class);
  URL registryUrl=mock(URL.class);
  when(registryUrl.toFullString()).thenReturn("test://localhost:8080");
  URL providerUrl=mock(URL.class);
  when(providerUrl.getServiceKey()).thenReturn("org.apache.dubbo.BarService");
  when(providerUrl.toFullString()).thenReturn("dubbo://localhost:8888/org.apache.dubbo.BarService");
  when(providerInvoker.getUrl()).thenReturn(providerUrl);
  ProviderConsumerRegTable.registerProvider(providerInvoker,registryUrl,providerUrl);
  Registry registry=mock(Registry.class);
  TestRegistryFactory.registry=registry;
  Online online=new Online();
  String output=online.execute(mock(CommandContext.class),new String[]{"org.apache.dubbo.BarService"});
  assertThat(output,equalTo("OK"));
  for (  ProviderInvokerWrapper wrapper : getProviderInvoker("org.apache.dubbo.BarService")) {
    assertTrue(wrapper.isReg());
  }
}
