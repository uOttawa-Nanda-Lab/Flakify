@Test public void createExistingClosedBatch() throws Exception {
  TransactionBatch existingBatch=mock(TransactionBatch.class);
  Transaction tx=mock(Transaction.class);
  ArgumentCaptor<Synchronization> capturedSync=ArgumentCaptor.forClass(Synchronization.class);
  InfinispanBatcher.setCurrentBatch(existingBatch);
  when(existingBatch.getState()).thenReturn(Batch.State.CLOSED);
  when(this.tm.getTransaction()).thenReturn(tx);
  try (TransactionBatch batch=this.batcher.createBatch()){
    verify(this.tm).begin();
    verify(tx).registerSynchronization(capturedSync.capture());
    assertSame(tx,batch.getTransaction());
    assertSame(batch,InfinispanBatcher.getCurrentBatch());
  }
  finally {
    capturedSync.getValue().afterCompletion(Status.STATUS_COMMITTED);
  }
  verify(tx).commit();
  assertNull(InfinispanBatcher.getCurrentBatch());
}
