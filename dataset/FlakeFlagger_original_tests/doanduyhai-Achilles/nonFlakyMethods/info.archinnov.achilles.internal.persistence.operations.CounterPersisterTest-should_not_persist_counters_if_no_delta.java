@Test public void should_not_persist_counters_if_no_delta() throws Exception {
  final long delta=0L;
  final Counter counter=InternalCounterBuilder.incr(delta);
  when(counterMeta.getValueFromField(entity)).thenReturn(counter);
  persister.persistCounters(context,asList(counterMeta));
  verify(context,never()).bindForSimpleCounterIncrement(counterMeta,delta);
}
