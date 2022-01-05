@Test public void resumeNonTxBatch() throws Exception {
  TransactionBatch existingBatch=mock(TransactionBatch.class);
  InfinispanBatcher.setCurrentBatch(existingBatch);
  TransactionBatch batch=mock(TransactionBatch.class);
  try (BatchContext context=this.batcher.resumeBatch(batch)){
    verifyZeroInteractions(this.tm);
    assertSame(batch,InfinispanBatcher.getCurrentBatch());
  }
   verifyZeroInteractions(this.tm);
  assertSame(existingBatch,InfinispanBatcher.getCurrentBatch());
}
