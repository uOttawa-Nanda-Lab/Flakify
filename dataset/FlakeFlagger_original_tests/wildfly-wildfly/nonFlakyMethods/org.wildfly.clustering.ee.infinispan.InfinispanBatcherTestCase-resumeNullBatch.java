@Test public void resumeNullBatch() throws Exception {
  TransactionBatch batch=mock(TransactionBatch.class);
  InfinispanBatcher.setCurrentBatch(batch);
  try (BatchContext context=this.batcher.resumeBatch(null)){
    verifyZeroInteractions(this.tm);
    assertNull(InfinispanBatcher.getCurrentBatch());
  }
   verifyZeroInteractions(this.tm);
  assertSame(batch,InfinispanBatcher.getCurrentBatch());
}
