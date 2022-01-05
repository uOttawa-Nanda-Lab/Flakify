@Test public void should_get_varying_component_name() throws Exception {
  when(sliceQuery.getClusteringsFrom()).thenReturn(Arrays.<Object>asList(11L,2,"a"));
  when(sliceQuery.getClusteringsTo()).thenReturn(Arrays.<Object>asList(11L,2));
  when(sliceQuery.getIdMeta().getVaryingComponentNameForQuery(2)).thenReturn("name");
  cqlSliceQuery=new CQLSliceQuery<ClusteredEntity>(sliceQuery,EACH_QUORUM);
  assertThat(cqlSliceQuery.getVaryingComponentName()).isEqualTo("name");
}
