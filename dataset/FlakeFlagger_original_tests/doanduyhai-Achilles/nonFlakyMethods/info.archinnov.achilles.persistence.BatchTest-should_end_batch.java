@Test public void should_end_batch() throws Exception {
  BatchingFlushContext newFlushContext=mock(BatchingFlushContext.class);
  when(flushContext.duplicateWithNoData(ONE)).thenReturn(newFlushContext);
  manager.endBatch();
  verify(flushContext).endBatch();
  assertThat(manager.flushContext).isSameAs(newFlushContext);
}
