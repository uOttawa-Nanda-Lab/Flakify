@Test public void createBatchClose() throws Exception {
  Transaction tx=mock(Transaction.class);
  ArgumentCaptor<Synchronization> capturedSync=ArgumentCaptor.forClass(Synchronization.class);
  when(this.tm.getTransaction()).thenReturn(tx);
  try (TransactionBatch batch=this.batcher.createBatch()){
    verify(this.tm).begin();
    verify(tx).registerSynchronization(capturedSync.capture());
    assertSame(tx,batch.getTransaction());
  }
  finally {
    capturedSync.getValue().afterCompletion(Status.STATUS_COMMITTED);
  }
  verify(tx).commit();
  assertNull(InfinispanBatcher.getCurrentBatch());
}
