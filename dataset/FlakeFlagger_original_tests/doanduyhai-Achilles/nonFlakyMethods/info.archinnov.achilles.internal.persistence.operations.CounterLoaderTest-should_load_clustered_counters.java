@Test public void should_load_clustered_counters() throws Exception {
  Row row=mock(Row.class);
  when(context.getEntityMeta()).thenReturn(meta);
  when(context.getPrimaryKey()).thenReturn(primaryKey);
  when(overrider.getReadLevel(context)).thenReturn(ONE);
  when(context.getClusteredCounter()).thenReturn(row);
  when(meta.instanciate()).thenReturn(entity);
  when(meta.getIdMeta()).thenReturn(idMeta);
  when(context.getAllCountersMeta()).thenReturn(Arrays.asList(counterMeta));
  Object actual=loader.loadClusteredCounters(context);
  assertThat(actual).isSameAs(entity);
  verify(mapper).setCounterToEntity(counterMeta,entity,row);
}
