@Test public void should_return_map_wrapper() throws Throwable {
  PropertyMeta propertyMeta=completeBean(Integer.class,String.class).field("preferences").accessors().type(PropertyType.MAP).build();
  getterMetas.put(propertyMeta.getGetter(),propertyMeta);
  rawValue=new HashMap<Integer,String>();
  when(invoker.getValueFromField(target,propertyMeta.getField())).thenReturn(rawValue);
  Object actual=interceptor.intercept(target,propertyMeta.getGetter(),args,proxy);
  assertThat(actual).isInstanceOf(MapWrapper.class);
}
