@Test public void createBatchDiscard() throws Exception {
  Transaction tx=mock(Transaction.class);
  ArgumentCaptor<Synchronization> capturedSync=ArgumentCaptor.forClass(Synchronization.class);
  when(this.tm.getTransaction()).thenReturn(tx);
  try (TransactionBatch batch=this.batcher.createBatch()){
    verify(this.tm).begin();
    verify(tx).registerSynchronization(capturedSync.capture());
    assertSame(tx,batch.getTransaction());
    batch.discard();
  }
  finally {
    capturedSync.getValue().afterCompletion(Status.STATUS_ROLLEDBACK);
  }
  verify(tx,never()).commit();
  verify(tx).rollback();
  assertNull(InfinispanBatcher.getCurrentBatch());
}
