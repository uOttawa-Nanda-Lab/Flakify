@Test public void should_return_null_for_map_property() throws Throwable {
  PropertyMeta propertyMeta=completeBean(Integer.class,String.class).field("preferences").accessors().type(PropertyType.MAP).build();
  getterMetas.put(propertyMeta.getGetter(),propertyMeta);
  when(invoker.getValueFromField(target,propertyMeta.getField())).thenReturn(null);
  Object actual=interceptor.intercept(target,propertyMeta.getGetter(),args,proxy);
  assertThat(actual).isNull();
}
