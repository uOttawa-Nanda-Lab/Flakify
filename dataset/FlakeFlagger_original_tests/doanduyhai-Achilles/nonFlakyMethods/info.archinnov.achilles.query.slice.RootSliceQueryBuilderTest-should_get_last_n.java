@Test public void should_get_last_n() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  when(sliceQueryExecutor.get(anySliceQuery())).thenReturn(result);
  builder.partitionComponentsInternal(partitionKey).getLast(6);
  assertThat(Whitebox.getInternalState(builder,"ordering")).isEqualTo(DESCENDING);
  assertThat(Whitebox.getInternalState(builder,"limit")).isEqualTo(6);
}
