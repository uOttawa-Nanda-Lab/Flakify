@Test public void should_load_clustered_counter_column() throws Exception {
  final long counterValue=11L;
  when(overrider.getReadLevel(context,counterMeta)).thenReturn(ONE);
  when(context.getClusteredCounterColumn(counterMeta)).thenReturn(counterValue);
  loader.loadClusteredCounterColumn(context,entity,counterMeta);
  verify(mapper).setCounterToEntity(counterMeta,entity,counterValue);
}
