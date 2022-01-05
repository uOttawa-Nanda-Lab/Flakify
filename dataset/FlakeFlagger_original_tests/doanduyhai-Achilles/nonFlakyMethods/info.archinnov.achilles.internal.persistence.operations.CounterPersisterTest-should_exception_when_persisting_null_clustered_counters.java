@Test(expected=IllegalStateException.class) public void should_exception_when_persisting_null_clustered_counters() throws Exception {
  when(context.getAllCountersMeta()).thenReturn(asList(counterMeta,counterMeta));
  persister.persistClusteredCounters(context);
}
