@Test public void should_not_persist_counters_if_null_counter() throws Exception {
  persister.persistCounters(context,asList(counterMeta));
  verify(context,never()).bindForSimpleCounterIncrement(eq(counterMeta),anyLong());
}
