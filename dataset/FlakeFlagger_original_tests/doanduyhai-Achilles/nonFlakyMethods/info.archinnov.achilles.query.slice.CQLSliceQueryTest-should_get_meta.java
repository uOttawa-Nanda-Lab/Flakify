@Test public void should_get_meta() throws Exception {
  EntityMeta meta=new EntityMeta();
  when(sliceQuery.getMeta()).thenReturn(meta);
  cqlSliceQuery=new CQLSliceQuery<ClusteredEntity>(sliceQuery,EACH_QUORUM);
  assertThat(cqlSliceQuery.getMeta()).isSameAs(meta);
}
