@Test public void should_remove_clustered_counter() throws Exception {
  when(entityMeta.isClusteredCounter()).thenReturn(true);
  persister.remove(context);
  verify(context).bindForClusteredCounterRemoval();
}
