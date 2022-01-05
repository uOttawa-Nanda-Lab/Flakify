@Test public void should_load_and_return_simple_property() throws Throwable {
  PropertyMeta propertyMeta=completeBean(Void.class,String.class).field("name").accessors().type(PropertyType.SIMPLE).build();
  getterMetas.put(propertyMeta.getGetter(),propertyMeta);
  when(invoker.getValueFromField(target,propertyMeta.getField())).thenReturn(rawValue);
  Object actual=interceptor.intercept(target,propertyMeta.getGetter(),args,proxy);
  assertThat(actual).isEqualTo(rawValue);
  verify(loader).loadPropertyIntoObject(context,target,propertyMeta);
}
