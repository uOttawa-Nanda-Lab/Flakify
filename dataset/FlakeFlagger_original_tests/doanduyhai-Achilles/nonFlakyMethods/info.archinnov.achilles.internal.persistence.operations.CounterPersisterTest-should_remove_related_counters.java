@Test public void should_remove_related_counters() throws Exception {
  when(meta.getAllCounterMetas()).thenReturn(asList(counterMeta,counterMeta2));
  persister.removeRelatedCounters(context);
  verify(context).bindForSimpleCounterRemoval(counterMeta);
  verify(context).bindForSimpleCounterRemoval(counterMeta2);
}
