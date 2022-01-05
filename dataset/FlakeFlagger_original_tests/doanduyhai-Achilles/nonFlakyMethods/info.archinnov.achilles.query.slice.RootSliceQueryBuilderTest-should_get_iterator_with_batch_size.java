@Test public void should_get_iterator_with_batch_size() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  when(sliceQueryExecutor.iterator(anySliceQuery())).thenReturn(iterator);
  Iterator<ClusteredEntity> actual=builder.partitionComponentsInternal(partitionKey).iterator(7);
  assertThat(Whitebox.getInternalState(builder,"batchSize")).isEqualTo(7);
  assertThat(actual).isSameAs(iterator);
}
