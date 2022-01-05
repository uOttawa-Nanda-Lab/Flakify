@Test public void should_get_first_n() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  when(sliceQueryExecutor.get(anySliceQuery())).thenReturn(result);
  builder.partitionComponentsInternal(partitionKey).getFirst(3);
  assertThat(Whitebox.<Integer>getInternalState(builder,"limit")).isEqualTo(3);
}
