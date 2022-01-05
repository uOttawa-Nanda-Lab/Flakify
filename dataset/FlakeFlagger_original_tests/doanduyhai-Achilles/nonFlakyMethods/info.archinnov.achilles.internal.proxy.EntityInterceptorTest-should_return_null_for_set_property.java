@Test public void should_return_null_for_set_property() throws Throwable {
  PropertyMeta propertyMeta=completeBean(Void.class,String.class).field("followers").accessors().type(PropertyType.SET).build();
  getterMetas.put(propertyMeta.getGetter(),propertyMeta);
  when(proxy.invoke(target,args)).thenReturn(null);
  Object actual=interceptor.intercept(target,propertyMeta.getGetter(),args,proxy);
  assertThat(actual).isNull();
}
