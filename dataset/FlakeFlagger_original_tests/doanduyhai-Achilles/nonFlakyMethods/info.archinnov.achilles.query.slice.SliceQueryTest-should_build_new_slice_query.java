@Test public void should_build_new_slice_query() throws Exception {
  PropertyMeta idMeta=mock(PropertyMeta.class);
  EntityMeta meta=new EntityMeta();
  meta.setIdMeta(idMeta);
  List<Object> fromComponents=Arrays.<Object>asList(11L,"a");
  List<Object> toComponents=Arrays.<Object>asList(11L,"b");
  when(idMeta.encodeToComponents(fromComponents)).thenReturn(fromComponents);
  when(idMeta.encodeToComponents(toComponents)).thenReturn(toComponents);
  when(idMeta.getClusteringOrders()).thenReturn(Arrays.asList(new ClusteringOrder("clust",Sorting.DESC)));
  SliceQuery<ClusteredEntity> sliceQuery=new SliceQuery<>(ClusteredEntity.class,meta,Arrays.<Object>asList(11L),Arrays.<Object>asList("a"),Arrays.<Object>asList("b"),OrderingMode.ASCENDING,BoundingMode.INCLUSIVE_BOUNDS,null,100,99,false);
  assertThat(sliceQuery.getEntityClass()).isSameAs(ClusteredEntity.class);
  assertThat(sliceQuery.getBatchSize()).isEqualTo(99);
  assertThat(sliceQuery.getBounding()).isEqualTo(BoundingMode.INCLUSIVE_BOUNDS);
  assertThat(sliceQuery.getClusteringsFrom()).containsExactly(11L,"a");
  assertThat(sliceQuery.getClusteringsTo()).containsExactly(11L,"b");
  assertThat(sliceQuery.getConsistencyLevel()).isNull();
  assertThat(sliceQuery.getLimit()).isEqualTo(100);
  assertThat(sliceQuery.getMeta()).isSameAs(meta);
  assertThat(sliceQuery.getOrdering()).isSameAs(OrderingMode.DESCENDING);
  assertThat(sliceQuery.getPartitionComponents()).containsExactly(11L);
  assertThat(sliceQuery.isLimitSet()).isFalse();
}
