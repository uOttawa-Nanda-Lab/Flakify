@Test public void should_persist_with_options() throws Exception {
  when(proxifier.buildProxyWithAllFieldsLoadedExceptCounters(entity,entityFacade)).thenReturn(entity);
  when(facade.persist(entity)).thenReturn(entity);
  CompleteBean actual=manager.persist(entity,OptionsBuilder.withConsistency(EACH_QUORUM).withTtl(150).withTimestamp(100L));
  Options options=optionsCaptor.getValue();
  assertThat(actual).isSameAs(entity);
  verify(entityValidator).validateEntity(entity,entityMetaMap);
  verify(optionsValidator).validateOptionsForInsert(entity,entityMetaMap,options);
  verify(proxifier).ensureNotProxy(entity);
  verify(facade).persist(entity);
  assertThat(options.getConsistencyLevel().get()).isEqualTo(EACH_QUORUM);
  assertThat(options.getTtl().get()).isEqualTo(150);
  assertThat(options.getTimestamp().get()).isEqualTo(100L);
}
