@Test public void should_remove_first_n() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  builder.partitionComponentsInternal(partitionKey).removeFirst(9);
  assertThat(Whitebox.getInternalState(builder,"limit")).isEqualTo(9);
  verify(sliceQueryExecutor).remove(anySliceQuery());
}
