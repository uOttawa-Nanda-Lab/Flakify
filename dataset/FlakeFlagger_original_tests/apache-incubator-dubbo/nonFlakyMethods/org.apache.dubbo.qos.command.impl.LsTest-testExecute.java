@Test public void testExecute() throws Exception {
  ConsumerModel consumerModel=mock(ConsumerModel.class);
  when(consumerModel.getServiceName()).thenReturn("org.apache.dubbo.FooService");
  ProviderModel providerModel=mock(ProviderModel.class);
  when(providerModel.getServiceName()).thenReturn("org.apache.dubbo.BarService");
  ApplicationModel.initConsumerModel("org.apache.dubbo.FooService",consumerModel);
  ApplicationModel.initProviderModel("org.apache.dubbo.BarService",providerModel);
  Invoker providerInvoker=mock(Invoker.class);
  URL registryUrl=mock(URL.class);
  when(registryUrl.toFullString()).thenReturn("test://localhost:8080");
  URL providerUrl=mock(URL.class);
  when(providerUrl.getServiceKey()).thenReturn("org.apache.dubbo.BarService");
  when(providerUrl.toFullString()).thenReturn("dubbo://localhost:8888/org.apache.dubbo.BarService");
  when(providerInvoker.getUrl()).thenReturn(providerUrl);
  ProviderConsumerRegTable.registerProvider(providerInvoker,registryUrl,providerUrl);
  for (  ProviderInvokerWrapper wrapper : getProviderInvoker("org.apache.dubbo.BarService")) {
    wrapper.setReg(true);
  }
  Invoker consumerInvoker=mock(Invoker.class);
  URL consumerUrl=mock(URL.class);
  when(consumerUrl.getServiceKey()).thenReturn("org.apache.dubbo.FooService");
  when(consumerUrl.toFullString()).thenReturn("dubbo://localhost:8888/org.apache.dubbo.FooService");
  when(consumerInvoker.getUrl()).thenReturn(consumerUrl);
  RegistryDirectory directory=mock(RegistryDirectory.class);
  Map invokers=Mockito.mock(Map.class);
  when(invokers.size()).thenReturn(100);
  when(directory.getUrlInvokerMap()).thenReturn(invokers);
  ProviderConsumerRegTable.registerConsumer(consumerInvoker,registryUrl,consumerUrl,directory);
  Ls ls=new Ls();
  String output=ls.execute(mock(CommandContext.class),null);
  assertThat(output,containsString("org.apache.dubbo.FooService|100"));
  assertThat(output,containsString("org.apache.dubbo.BarService| Y"));
}
