@Test public void should_return_null_when_no_first() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  when(sliceQueryExecutor.get(anySliceQuery())).thenReturn(new ArrayList<ClusteredEntity>());
  ClusteredEntity actual=builder.partitionComponentsInternal(partitionKey).getFirstOccurence();
  assertThat(actual).isNull();
  assertThat(Whitebox.getInternalState(builder,"limit")).isEqualTo(1);
}
