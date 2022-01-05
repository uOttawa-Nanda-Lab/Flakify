@Test public void createExistingActiveBatch() throws Exception {
  TransactionBatch existingBatch=mock(TransactionBatch.class);
  InfinispanBatcher.setCurrentBatch(existingBatch);
  when(existingBatch.getState()).thenReturn(Batch.State.ACTIVE);
  when(existingBatch.interpose()).thenReturn(existingBatch);
  TransactionBatch result=this.batcher.createBatch();
  verify(existingBatch).interpose();
  verifyZeroInteractions(this.tm);
  assertSame(existingBatch,result);
}
