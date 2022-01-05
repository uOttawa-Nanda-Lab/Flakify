@Test public void should_load_clustered_counter_entity() throws Exception {
  when(meta.isClusteredCounter()).thenReturn(true);
  when(counterLoader.loadClusteredCounters(context)).thenReturn(entity);
  CompleteBean actual=loader.load(context,CompleteBean.class);
  assertThat(actual).isSameAs(entity);
  verifyZeroInteractions(mapper);
}
