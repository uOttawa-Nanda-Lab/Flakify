@Test public void should_remove_n() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  builder.partitionComponentsInternal(partitionKey).remove(8);
  assertThat(Whitebox.getInternalState(builder,"limit")).isEqualTo(8);
  verify(sliceQueryExecutor).remove(anySliceQuery());
}
