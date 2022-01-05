@Test public void mutateTransactional(){
  AdvancedCache<Object,Object> cache=mock(AdvancedCache.class);
  Object id=new Object();
  Object value=new Object();
  Configuration config=new ConfigurationBuilder().transaction().transactionMode(TransactionMode.TRANSACTIONAL).build();
  when(cache.getCacheConfiguration()).thenReturn(config);
  Mutator mutator=new CacheEntryMutator<>(cache,id,value);
  when(cache.getAdvancedCache()).thenReturn(cache);
  when(cache.withFlags(Flag.IGNORE_RETURN_VALUES,Flag.FAIL_SILENTLY)).thenReturn(cache);
  mutator.mutate();
  verify(cache).put(same(id),same(value));
  mutator.mutate();
  verify(cache,times(1)).put(same(id),same(value));
  mutator.mutate();
  verify(cache,times(1)).put(same(id),same(value));
}
