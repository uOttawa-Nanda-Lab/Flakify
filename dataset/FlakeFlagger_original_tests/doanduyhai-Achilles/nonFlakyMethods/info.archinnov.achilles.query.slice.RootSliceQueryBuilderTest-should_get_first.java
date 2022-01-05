@Test public void should_get_first() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  ClusteredEntity entity=new ClusteredEntity();
  when(sliceQueryExecutor.get(anySliceQuery())).thenReturn(Arrays.asList(entity));
  ClusteredEntity actual=builder.partitionComponentsInternal(partitionKey).getFirstOccurence();
  assertThat(actual).isSameAs(entity);
  assertThat(Whitebox.<Integer>getInternalState(builder,"limit")).isEqualTo(1);
}
