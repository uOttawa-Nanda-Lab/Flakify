@Test public void should_remove() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  builder.partitionComponentsInternal(partitionKey).remove();
  verify(sliceQueryExecutor).remove(anySliceQuery());
}
