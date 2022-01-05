@Test public void should_update_with_options() throws Exception {
  when(proxifier.getRealObject(entity)).thenReturn(entity);
  manager.update(entity,OptionsBuilder.withConsistency(EACH_QUORUM).withTtl(150).withTimestamp(100L));
  Options options=optionsCaptor.getValue();
  verify(proxifier).ensureProxy(entity);
  verify(entityValidator).validateEntity(entity,entityMetaMap);
  verify(optionsValidator).validateOptionsForUpdate(entity,entityMetaMap,options);
  verify(facade).update(entity);
  assertThat(options.getConsistencyLevel().get()).isEqualTo(EACH_QUORUM);
  assertThat(options.getTtl().get()).isEqualTo(150);
  assertThat(options.getTimestamp().get()).isEqualTo(100L);
}
