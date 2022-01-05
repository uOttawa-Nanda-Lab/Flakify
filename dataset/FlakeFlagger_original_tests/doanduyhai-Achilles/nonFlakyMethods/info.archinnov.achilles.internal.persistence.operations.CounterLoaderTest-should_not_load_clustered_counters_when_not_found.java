@Test public void should_not_load_clustered_counters_when_not_found() throws Exception {
  when(context.getEntityMeta()).thenReturn(meta);
  when(context.getPrimaryKey()).thenReturn(primaryKey);
  when(overrider.getReadLevel(context)).thenReturn(ONE);
  Object actual=loader.loadClusteredCounters(context);
  assertThat(actual).isNull();
  verifyZeroInteractions(mapper);
}
