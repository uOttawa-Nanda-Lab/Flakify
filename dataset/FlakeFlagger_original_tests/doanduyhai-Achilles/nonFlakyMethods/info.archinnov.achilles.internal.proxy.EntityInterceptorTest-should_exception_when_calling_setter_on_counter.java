@Test public void should_exception_when_calling_setter_on_counter() throws Throwable {
  PropertyMeta propertyMeta=completeBean(Void.class,Counter.class).field("count").accessors().type(PropertyType.COUNTER).build();
  args=new Object[]{null};
  setterMetas.put(propertyMeta.getGetter(),propertyMeta);
  exception.expect(UnsupportedOperationException.class);
  exception.expectMessage("Cannot set value directly to a Counter type. Please call the getter first to get handle on the wrapper");
  interceptor.intercept(target,propertyMeta.getGetter(),args,proxy);
}
