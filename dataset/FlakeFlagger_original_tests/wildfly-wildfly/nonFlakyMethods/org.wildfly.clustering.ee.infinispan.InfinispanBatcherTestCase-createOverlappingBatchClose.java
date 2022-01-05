@SuppressWarnings("resource") @Test public void createOverlappingBatchClose() throws Exception {
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
      batch.close();
      verify(tx,never()).rollback();
      verify(tx,never()).commit();
    }
   }
  finally {
    capturedSync.getValue().afterCompletion(Status.STATUS_COMMITTED);
  }
  verify(tx,never()).rollback();
  verify(tx).commit();
  assertNull(InfinispanBatcher.getCurrentBatch());
}
