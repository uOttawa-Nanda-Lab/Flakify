@Test public void createNestedBatchClose() throws Exception {
  Transaction tx=mock(Transaction.class);
  ArgumentCaptor<Synchronization> capturedSync=ArgumentCaptor.forClass(Synchronization.class);
  when(this.tm.getTransaction()).thenReturn(tx);
  try (TransactionBatch outerBatch=this.batcher.createBatch()){
    verify(this.tm).begin();
    verify(tx).registerSynchronization(capturedSync.capture());
    reset(this.tm);
    assertSame(tx,outerBatch.getTransaction());
    when(this.tm.getTransaction()).thenReturn(tx);
    try (TransactionBatch innerBatch=this.batcher.createBatch()){
      verify(this.tm,never()).begin();
      verify(this.tm,never()).suspend();
    }
     verify(tx,never()).rollback();
    verify(tx,never()).commit();
  }
  finally {
    capturedSync.getValue().afterCompletion(Status.STATUS_COMMITTED);
  }
  verify(tx,never()).rollback();
  verify(tx).commit();
  assertNull(InfinispanBatcher.getCurrentBatch());
}
