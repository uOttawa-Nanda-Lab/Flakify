@Test public void should_get_entity_class() throws Exception {
  when(sliceQuery.getEntityClass()).thenReturn(ClusteredEntity.class);
  cqlSliceQuery=new CQLSliceQuery<ClusteredEntity>(sliceQuery,EACH_QUORUM);
  assertThat(cqlSliceQuery.getEntityClass()).isSameAs(ClusteredEntity.class);
}
