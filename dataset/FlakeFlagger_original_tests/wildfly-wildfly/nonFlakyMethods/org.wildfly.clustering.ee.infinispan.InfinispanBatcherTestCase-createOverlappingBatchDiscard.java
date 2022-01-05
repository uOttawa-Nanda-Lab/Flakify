@SuppressWarnings("resource") @Test public void createOverlappingBatchDiscard() throws Exception {
  Transaction tx=mock(Transaction.class);
  ArgumentCaptor<Synchronization> capturedSync=ArgumentCaptor.forClass(Synchronization.class);
  when(this.tm.getTransaction()).thenReturn(tx);
  TransactionBatch batch=this.batcher.createBatch();
  verify(this.tm).begin();
  verify(tx).registerSynchronization(capturedSync.capture());
  reset(this.tm);
  try {
    assertSame(tx,batch.getTransaction());
    when(this.tm.getTransaction()).thenReturn(tx);
    when(tx.getStatus()).thenReturn(Status.STATUS_ACTIVE);
    try (TransactionBatch innerBatch=this.batcher.createBatch()){
      verify(this.tm,never()).begin();
      innerBatch.discard();
      batch.close();
      verify(tx,never()).commit();
      verify(tx,never()).rollback();
    }
   }
  finally {
    capturedSync.getValue().afterCompletion(Status.STATUS_ROLLEDBACK);
  }
  verify(tx).rollback();
  verify(tx,never()).commit();
  assertNull(InfinispanBatcher.getCurrentBatch());
}
