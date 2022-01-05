@Test public void should_return_set_wrapper() throws Throwable {
  PropertyMeta propertyMeta=completeBean(Void.class,String.class).field("followers").accessors().type(PropertyType.SET).build();
  getterMetas.put(propertyMeta.getGetter(),propertyMeta);
  rawValue=new HashSet<String>();
  when(invoker.getValueFromField(target,propertyMeta.getField())).thenReturn(rawValue);
  Object actual=interceptor.intercept(target,propertyMeta.getGetter(),args,proxy);
  assertThat(actual).isInstanceOf(SetWrapper.class);
}
