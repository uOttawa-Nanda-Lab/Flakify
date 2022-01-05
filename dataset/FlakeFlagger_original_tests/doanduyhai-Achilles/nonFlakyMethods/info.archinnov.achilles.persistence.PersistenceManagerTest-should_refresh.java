@Test public void should_refresh() throws Exception {
  when(proxifier.getRealObject(entity)).thenReturn(entity);
  manager.refresh(entity);
  verify(entityValidator).validateEntity(entity,entityMetaMap);
  verify(proxifier).ensureProxy(entity);
  verify(facade).refresh(entity);
  Options options=optionsCaptor.getValue();
  assertThat(options.getConsistencyLevel().isPresent()).isFalse();
  assertThat(options.getTtl().isPresent()).isFalse();
  assertThat(options.getTimestamp().isPresent()).isFalse();
}
