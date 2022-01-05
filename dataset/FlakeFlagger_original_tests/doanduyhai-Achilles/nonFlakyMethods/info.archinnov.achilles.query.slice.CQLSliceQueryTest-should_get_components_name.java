@Test public void should_get_components_name() throws Exception {
  when(sliceQuery.getIdMeta().getComponentNames()).thenReturn(Arrays.asList("id","count","name"));
  cqlSliceQuery=new CQLSliceQuery<ClusteredEntity>(sliceQuery,EACH_QUORUM);
  assertThat(cqlSliceQuery.getComponentNames()).containsExactly("id","count","name");
}
