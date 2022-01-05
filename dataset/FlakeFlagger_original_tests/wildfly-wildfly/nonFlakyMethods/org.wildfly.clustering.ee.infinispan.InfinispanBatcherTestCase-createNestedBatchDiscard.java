@Test public void createNestedBatchDiscard() throws Exception {
  Transaction tx=mock(Transaction.class);
  ArgumentCaptor<Synchronization> capturedSync=ArgumentCaptor.forClass(Synchronization.class);
  when(this.tm.getTransaction()).thenReturn(tx);
  try (TransactionBatch outerBatch=this.batcher.createBatch()){
    verify(this.tm).begin();
    verify(tx).registerSynchronization(capturedSync.capture());
    reset(this.tm);
    assertSame(tx,outerBatch.getTransaction());
    when(tx.getStatus()).thenReturn(Status.STATUS_ACTIVE);
    when(this.tm.getTransaction()).thenReturn(tx);
    try (TransactionBatch innerBatch=this.batcher.createBatch()){
      verify(this.tm,never()).begin();
      innerBatch.discard();
    }
     verify(tx,never()).commit();
    verify(tx,never()).rollback();
  }
  finally {
    capturedSync.getValue().afterCompletion(Status.STATUS_ROLLEDBACK);
  }
  verify(tx).rollback();
  verify(tx,never()).commit();
  assertNull(InfinispanBatcher.getCurrentBatch());
}
