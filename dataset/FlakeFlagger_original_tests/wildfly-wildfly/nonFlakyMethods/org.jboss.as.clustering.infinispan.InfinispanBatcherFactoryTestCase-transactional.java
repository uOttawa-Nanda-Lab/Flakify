@Test public void transactional(){
  @SuppressWarnings("unchecked") AdvancedCache<Object,Object> cache=mock(AdvancedCache.class);
  Configuration configuration=new ConfigurationBuilder().transaction().transactionMode(TransactionMode.TRANSACTIONAL).build();
  when(cache.getAdvancedCache()).thenReturn(cache);
  when(cache.getCacheConfiguration()).thenReturn(configuration);
  Batcher<TransactionBatch> batcher=this.factory.createBatcher(cache);
  assertNotNull(batcher);
}
