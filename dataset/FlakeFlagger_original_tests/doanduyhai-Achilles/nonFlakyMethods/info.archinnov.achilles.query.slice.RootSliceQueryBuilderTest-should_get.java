@Test public void should_get() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  when(sliceQueryExecutor.get(anySliceQuery())).thenReturn(result);
  List<ClusteredEntity> actual=builder.partitionComponentsInternal(partitionKey).get();
  assertThat(actual).isSameAs(result);
}
