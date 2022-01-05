@Test public void should_get_last_with_clustering_components() throws Exception {
  Long partitionKey=RandomUtils.nextLong();
  ClusteredEntity entity=new ClusteredEntity();
  when(sliceQueryExecutor.get(anySliceQuery())).thenReturn(Arrays.asList(entity));
  Object[] clusteringComponents=new Object[]{1,"name"};
  ClusteredEntity actual=builder.partitionComponentsInternal(partitionKey).getLastOccurence(clusteringComponents);
  assertThat(actual).isSameAs(entity);
  assertThat(Whitebox.getInternalState(builder,"ordering")).isEqualTo(DESCENDING);
  assertThat(Whitebox.getInternalState(builder,"limit")).isEqualTo(1);
  assertThat(Whitebox.<List<Object>>getInternalState(builder,"fromClusterings")).containsExactly(clusteringComponents);
  assertThat(Whitebox.<List<Object>>getInternalState(builder,"toClusterings")).containsExactly(clusteringComponents);
}
