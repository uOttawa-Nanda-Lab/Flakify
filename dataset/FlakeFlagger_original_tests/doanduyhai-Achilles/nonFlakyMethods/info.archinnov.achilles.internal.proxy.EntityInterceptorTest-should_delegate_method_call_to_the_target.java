@Test public void should_delegate_method_call_to_the_target() throws Throwable {
  PropertyMeta propertyMeta=completeBean(Void.class,String.class).field("name").accessors().type(PropertyType.SIMPLE).build();
  MethodProxy proxy=mock(MethodProxy.class);
  when(proxy.invoke(target,args)).thenReturn("name");
  Object name=interceptor.intercept(target,propertyMeta.getGetter(),args,proxy);
  assertThat(name).isEqualTo("name");
  verify(proxy).invokeSuper(target,args);
}
