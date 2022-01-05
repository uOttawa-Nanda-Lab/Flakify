@Test public void suspendNoBatch() throws Exception {
  TransactionBatch result=this.batcher.suspendBatch();
  verify(this.tm,never()).suspend();
  assertNull(result);
}
