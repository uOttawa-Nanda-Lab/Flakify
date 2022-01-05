@Test public void should_get_n() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  when(sliceQueryExecutor.get(anySliceQuery())).thenReturn(result);
  List<ClusteredEntity> actual=builder.partitionComponentsInternal(partitionKey).get(5);
  assertThat(actual).isSameAs(result);
  assertThat(Whitebox.<Integer>getInternalState(builder,"limit")).isEqualTo(5);
}
