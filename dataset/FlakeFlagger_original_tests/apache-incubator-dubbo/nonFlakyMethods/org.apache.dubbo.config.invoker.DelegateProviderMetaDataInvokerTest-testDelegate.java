@Test public void testDelegate() throws Exception {
  DelegateProviderMetaDataInvoker<Greeting> delegate=new DelegateProviderMetaDataInvoker<Greeting>(invoker,service);
  delegate.getInterface();
  Mockito.verify(invoker).getInterface();
  delegate.getUrl();
  Mockito.verify(invoker).getUrl();
  delegate.isAvailable();
  Mockito.verify(invoker).isAvailable();
  Invocation invocation=Mockito.mock(Invocation.class);
  delegate.invoke(invocation);
  Mockito.verify(invoker).invoke(invocation);
  delegate.destroy();
  Mockito.verify(invoker).destroy();
  assertThat(delegate.getMetadata(),sameInstance(service));
}
