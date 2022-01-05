@Test public void should_remove_last_n() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  builder.partitionComponentsInternal(partitionKey).removeLast(10);
  assertThat(Whitebox.getInternalState(builder,"ordering")).isEqualTo(DESCENDING);
  assertThat(Whitebox.getInternalState(builder,"limit")).isEqualTo(10);
  verify(sliceQueryExecutor).remove(anySliceQuery());
}
