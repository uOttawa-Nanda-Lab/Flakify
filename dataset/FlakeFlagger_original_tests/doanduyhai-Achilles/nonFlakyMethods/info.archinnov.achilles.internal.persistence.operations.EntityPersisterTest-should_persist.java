@Test public void should_persist() throws Exception {
  when(entityMeta.isClusteredCounter()).thenReturn(false);
  final List<PropertyMeta> counterMetas=asList(counterMeta);
  when(entityMeta.getAllCounterMetas()).thenReturn(counterMetas);
  persister.persist(context);
  verify(context).pushInsertStatement();
  verify(counterPersister).persistCounters(context,counterMetas);
}
