@Test public void should_get_first_n_with_clustering_components() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  when(sliceQueryExecutor.get(anySliceQuery())).thenReturn(result);
  Object[] clusteringComponents=new Object[]{1,"name"};
  builder.partitionComponentsInternal(partitionKey).getFirst(3,clusteringComponents);
  assertThat(Whitebox.getInternalState(builder,"limit")).isEqualTo(3);
  assertThat(Whitebox.<List<Object>>getInternalState(builder,"fromClusterings")).containsExactly(clusteringComponents);
  assertThat(Whitebox.<List<Object>>getInternalState(builder,"toClusterings")).containsExactly(clusteringComponents);
}
