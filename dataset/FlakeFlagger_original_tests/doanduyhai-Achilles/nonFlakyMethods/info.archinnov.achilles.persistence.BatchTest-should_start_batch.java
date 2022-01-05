@Test public void should_start_batch() throws Exception {
  BatchingFlushContext newFlushContext=mock(BatchingFlushContext.class);
  when(flushContext.duplicateWithNoData(ONE)).thenReturn(newFlushContext);
  manager.startBatch();
  assertThat(manager.flushContext).isSameAs(newFlushContext);
}
