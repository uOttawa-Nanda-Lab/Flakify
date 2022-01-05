@Test public void should_load_clustered_counter() throws Throwable {
  PropertyMeta propertyMeta=completeBean(Void.class,Counter.class).field("count").accessors().type(PropertyType.COUNTER).invoker(new ReflectionInvoker()).build();
  when(context.isClusteredCounter()).thenReturn(true);
  getterMetas.put(propertyMeta.getGetter(),propertyMeta);
  Object actual=interceptor.intercept(target,propertyMeta.getGetter(),args,proxy);
  assertThat(actual).isNull();
  InternalCounterImpl counter=(InternalCounterImpl)target.getCount();
  assertThat(counter.get()).isNull();
  assertThat(counter.getInternalCounterDelta()).isNull();
  verify(counterLoader).loadClusteredCounterColumn(context,target,propertyMeta);
}
