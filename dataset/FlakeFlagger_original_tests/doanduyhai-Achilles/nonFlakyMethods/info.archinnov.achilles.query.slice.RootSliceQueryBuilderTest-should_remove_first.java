@Test public void should_remove_first() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  builder.partitionComponentsInternal(partitionKey).removeFirstOccurence();
  assertThat(Whitebox.getInternalState(builder,"limit")).isEqualTo(1);
  verify(sliceQueryExecutor).remove(anySliceQuery());
}
