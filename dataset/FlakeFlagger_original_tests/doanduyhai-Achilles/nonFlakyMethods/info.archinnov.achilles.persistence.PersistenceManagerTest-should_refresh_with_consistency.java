@Test public void should_refresh_with_consistency() throws Exception {
  when(proxifier.getRealObject(entity)).thenReturn(entity);
  manager.refresh(entity,EACH_QUORUM);
  verify(entityValidator).validateEntity(entity,entityMetaMap);
  verify(proxifier).ensureProxy(entity);
  verify(facade).refresh(entity);
  Options options=optionsCaptor.getValue();
  assertThat(options.getConsistencyLevel().get()).isSameAs(EACH_QUORUM);
  assertThat(options.getTtl().isPresent()).isFalse();
  assertThat(options.getTimestamp().isPresent()).isFalse();
}
