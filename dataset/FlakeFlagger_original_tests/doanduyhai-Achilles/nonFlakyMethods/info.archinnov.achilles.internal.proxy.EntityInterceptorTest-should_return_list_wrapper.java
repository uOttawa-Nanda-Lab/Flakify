@Test public void should_return_list_wrapper() throws Throwable {
  PropertyMeta propertyMeta=completeBean(Void.class,String.class).field("friends").accessors().type(PropertyType.LIST).build();
  getterMetas.put(propertyMeta.getGetter(),propertyMeta);
  rawValue=new ArrayList<String>();
  when(invoker.getValueFromField(target,propertyMeta.getField())).thenReturn(rawValue);
  Object actual=interceptor.intercept(target,propertyMeta.getGetter(),args,proxy);
  assertThat(actual).isInstanceOf(ListWrapper.class);
}
