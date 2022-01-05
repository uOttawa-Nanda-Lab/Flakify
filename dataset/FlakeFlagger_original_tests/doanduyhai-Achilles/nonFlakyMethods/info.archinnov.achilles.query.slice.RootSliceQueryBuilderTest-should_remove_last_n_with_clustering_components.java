@Test public void should_remove_last_n_with_clustering_components() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  Object[] clusteringComponents=new Object[]{1,"name"};
  builder.partitionComponentsInternal(partitionKey).removeLast(10,clusteringComponents);
  assertThat(Whitebox.getInternalState(builder,"ordering")).isEqualTo(DESCENDING);
  assertThat(Whitebox.getInternalState(builder,"limit")).isEqualTo(10);
  assertThat(Whitebox.<List<Object>>getInternalState(builder,"fromClusterings")).containsExactly(clusteringComponents);
  assertThat(Whitebox.<List<Object>>getInternalState(builder,"toClusterings")).containsExactly(clusteringComponents);
  verify(sliceQueryExecutor).remove(anySliceQuery());
}
