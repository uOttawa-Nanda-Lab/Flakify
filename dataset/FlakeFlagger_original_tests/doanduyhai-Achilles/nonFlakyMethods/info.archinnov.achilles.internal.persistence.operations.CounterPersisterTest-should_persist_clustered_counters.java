@Test public void should_persist_clustered_counters() throws Exception {
  final long delta=10L;
  final Counter counter=InternalCounterBuilder.incr(delta);
  when(counterMeta.getValueFromField(entity)).thenReturn(counter);
  when(counterMeta3.getValueFromField(entity)).thenReturn(InternalCounterBuilder.incr(0L));
  when(context.getAllCountersMeta()).thenReturn(asList(counterMeta,counterMeta2,counterMeta3));
  persister.persistClusteredCounters(context);
  verify(context).pushClusteredCounterIncrementStatement(counterMeta,delta);
}
