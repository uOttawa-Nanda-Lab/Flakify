@Test public void should_get_last() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  ClusteredEntity entity=new ClusteredEntity();
  when(sliceQueryExecutor.get(anySliceQuery())).thenReturn(Arrays.asList(entity));
  ClusteredEntity actual=builder.partitionComponentsInternal(partitionKey).getLastOccurence();
  assertThat(actual).isSameAs(entity);
  assertThat(Whitebox.getInternalState(builder,"ordering")).isEqualTo(DESCENDING);
  assertThat(Whitebox.getInternalState(builder,"limit")).isEqualTo(1);
}
