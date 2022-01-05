@Test public void should_start_batch_with_consistency_level() throws Exception {
  BatchingFlushContext newFlushContext=mock(BatchingFlushContext.class);
  when(flushContext.duplicateWithNoData(EACH_QUORUM)).thenReturn(newFlushContext);
  manager.startBatch(EACH_QUORUM);
  assertThat(manager.flushContext).isSameAs(newFlushContext);
}
