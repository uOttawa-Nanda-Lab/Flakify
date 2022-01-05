@Test public void resumeBatchExisting() throws Exception {
  TransactionBatch existingBatch=mock(TransactionBatch.class);
  Transaction existingTx=mock(Transaction.class);
  InfinispanBatcher.setCurrentBatch(existingBatch);
  TransactionBatch batch=mock(TransactionBatch.class);
  Transaction tx=mock(Transaction.class);
  when(existingBatch.getTransaction()).thenReturn(existingTx);
  when(batch.getTransaction()).thenReturn(tx);
  when(this.tm.suspend()).thenReturn(existingTx);
  try (BatchContext context=this.batcher.resumeBatch(batch)){
    verify(this.tm).resume(tx);
    reset(this.tm);
    assertSame(batch,InfinispanBatcher.getCurrentBatch());
    when(this.tm.suspend()).thenReturn(tx);
  }
   verify(this.tm).resume(existingTx);
  assertSame(existingBatch,InfinispanBatcher.getCurrentBatch());
}
