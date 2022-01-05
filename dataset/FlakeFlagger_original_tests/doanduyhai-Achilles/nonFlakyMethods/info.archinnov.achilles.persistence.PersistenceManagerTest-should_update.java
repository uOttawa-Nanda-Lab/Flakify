@Test public void should_update() throws Exception {
  when(proxifier.isProxy(entity)).thenReturn(true);
  when(proxifier.getRealObject(entity)).thenReturn(entity);
  manager.update(entity);
  verify(proxifier).ensureProxy(entity);
  verify(entityValidator).validateEntity(entity,entityMetaMap);
  verify(facade).update(entity);
  Options options=optionsCaptor.getValue();
  assertThat(options.getConsistencyLevel().isPresent()).isFalse();
  assertThat(options.getTtl().isPresent()).isFalse();
  assertThat(options.getTimestamp().isPresent()).isFalse();
}
