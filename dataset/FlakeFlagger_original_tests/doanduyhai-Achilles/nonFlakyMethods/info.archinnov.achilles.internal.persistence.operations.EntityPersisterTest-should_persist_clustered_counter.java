@Test public void should_persist_clustered_counter() throws Exception {
  when(entityMeta.isClusteredCounter()).thenReturn(true);
  when(entityMeta.getAllCounterMetas()).thenReturn(asList(counterMeta));
  persister.persist(context);
  verify(counterPersister).persistClusteredCounters(context);
}
