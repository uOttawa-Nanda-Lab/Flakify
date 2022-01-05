@Test public void should_update_proxified_entity() throws Exception {
  when(proxifier.isProxy(entity)).thenReturn(true);
  when(proxifier.getRealObject(entity)).thenReturn(entity);
  when(proxifier.getInterceptor(entity)).thenReturn(interceptor);
  when(interceptor.getDirtyMap()).thenReturn(dirtyMap);
  when(meta.getAllCounterMetas()).thenReturn(allCounterMetas);
  PropertyMeta pm=PropertyMetaTestBuilder.completeBean(Void.class,UserBean.class).field("user").type(SIMPLE).accessors().build();
  DirtyChecker dirtyChecker=new SimpleDirtyChecker(pm);
  dirtyMap.put(pm.getGetter(),dirtyChecker);
  when(context.isClusteredCounter()).thenReturn(false);
  entityUpdater.update(context,entity);
  verify(context).setEntity(entity);
  verify(context).pushUpdateStatement(pmCaptor.capture());
  assertThat(pmCaptor.getValue()).containsOnly(pm);
  verify(counterPersister).persistCounters(context,allCounterMetas);
  verify(interceptor).setEntityOperations(context);
  verify(interceptor).setTarget(entity);
}
