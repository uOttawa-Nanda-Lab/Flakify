@Test public void should_create_iterator_for_clustered_entities() throws Exception {
  RegularStatementWrapper regularWrapper=mock(RegularStatementWrapper.class);
  when(generator.generateSelectSliceQuery(anySliceQuery())).thenReturn(regularWrapper);
  when(daoContext.execute(regularWrapper).iterator()).thenReturn(iterator);
  when(contextFactory.newContextForSliceQuery(ClusteredEntity.class,partitionComponents,LOCAL_QUORUM)).thenReturn(context);
  when(idMeta.getCQLComponentNames()).thenReturn(Arrays.asList("id","comp1"));
  Iterator<ClusteredEntity> iter=executor.iterator(sliceQuery);
  assertThat(iter).isNotNull();
  assertThat(iter).isInstanceOf(SliceQueryIterator.class);
}
