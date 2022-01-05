@Test public void should_clean_batch() throws Exception {
  BatchingFlushContext newFlushContext=mock(BatchingFlushContext.class);
  when(flushContext.duplicateWithNoData(ONE)).thenReturn(newFlushContext);
  manager.cleanBatch();
  assertThat(manager.flushContext).isSameAs(newFlushContext);
}
