@Test public void should_persist() throws Exception {
  when(proxifier.buildProxyWithAllFieldsLoadedExceptCounters(entity,entityFacade)).thenReturn(entity);
  when(facade.persist(entity)).thenReturn(entity);
  CompleteBean actual=manager.persist(entity);
  assertThat(actual).isSameAs(entity);
  verify(proxifier).ensureNotProxy(entity);
  verify(entityValidator).validateEntity(entity,entityMetaMap);
}
