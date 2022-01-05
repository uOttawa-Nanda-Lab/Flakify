@Test public void suspendBatch() throws Exception {
  TransactionBatch batch=mock(TransactionBatch.class);
  InfinispanBatcher.setCurrentBatch(batch);
  TransactionBatch result=this.batcher.suspendBatch();
  verify(this.tm).suspend();
  assertSame(batch,result);
  assertNull(InfinispanBatcher.getCurrentBatch());
}
