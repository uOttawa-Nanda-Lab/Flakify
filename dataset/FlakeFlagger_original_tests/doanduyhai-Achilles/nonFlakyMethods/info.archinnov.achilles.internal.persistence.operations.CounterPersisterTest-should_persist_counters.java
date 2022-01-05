@Test public void should_persist_counters() throws Exception {
  final long delta=10L;
  final Counter counter=InternalCounterBuilder.incr(delta);
  when(counterMeta.getValueFromField(entity)).thenReturn(counter);
  persister.persistCounters(context,asList(counterMeta));
  verify(context).bindForSimpleCounterIncrement(counterMeta,delta);
}
