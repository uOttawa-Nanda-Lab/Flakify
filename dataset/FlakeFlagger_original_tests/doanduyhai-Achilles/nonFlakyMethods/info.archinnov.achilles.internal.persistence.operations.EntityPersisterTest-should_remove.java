@Test public void should_remove() throws Exception {
  when(entityMeta.isClusteredCounter()).thenReturn(false);
  when(entityMeta.getTableName()).thenReturn("table");
  persister.remove(context);
  verify(context).bindForRemoval("table");
  verify(counterPersister).removeRelatedCounters(context);
}
