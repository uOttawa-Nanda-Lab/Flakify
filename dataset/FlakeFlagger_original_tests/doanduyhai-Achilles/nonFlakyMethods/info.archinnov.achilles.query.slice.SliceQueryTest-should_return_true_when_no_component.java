@Test public void should_return_true_when_no_component() throws Exception {
  PropertyMeta idMeta=mock(PropertyMeta.class);
  when(idMeta.getClusteringOrders()).thenReturn(Arrays.asList(new ClusteringOrder("clust",Sorting.DESC)));
  EntityMeta meta=new EntityMeta();
  meta.setIdMeta(idMeta);
  SliceQuery<ClusteredEntity> sliceQuery=new SliceQuery<ClusteredEntity>(ClusteredEntity.class,meta,Arrays.<Object>asList(11L),Arrays.asList(),Arrays.asList(),OrderingMode.ASCENDING,BoundingMode.INCLUSIVE_BOUNDS,null,100,99,false);
  assertThat(sliceQuery.hasNoComponent()).isTrue();
}
