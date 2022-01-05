@Test public void should_get_iterator() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  when(sliceQueryExecutor.iterator(anySliceQuery())).thenReturn(iterator);
  Iterator<ClusteredEntity> actual=builder.partitionComponentsInternal(partitionKey).iterator();
  assertThat(actual).isSameAs(iterator);
}
