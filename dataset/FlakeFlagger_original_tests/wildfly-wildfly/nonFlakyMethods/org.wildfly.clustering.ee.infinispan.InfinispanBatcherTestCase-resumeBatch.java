@Test public void resumeBatch() throws Exception {
  TransactionBatch batch=mock(TransactionBatch.class);
  Transaction tx=mock(Transaction.class);
  when(batch.getTransaction()).thenReturn(tx);
  try (BatchContext context=this.batcher.resumeBatch(batch)){
    verify(this.tm,never()).suspend();
    verify(this.tm).resume(tx);
    reset(this.tm);
    assertSame(batch,InfinispanBatcher.getCurrentBatch());
  }
   verify(this.tm).suspend();
  verify(this.tm,never()).resume(any());
  assertNull(InfinispanBatcher.getCurrentBatch());
}
