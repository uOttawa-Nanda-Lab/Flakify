@Test public void should_create_new_context_for_slice_query() throws Exception {
  Long primaryKey=RandomUtils.nextLong();
  List<Object> partitionComponents=Arrays.<Object>asList(primaryKey);
  when(invoker.instantiateEmbeddedIdWithPartitionComponents(idMeta,partitionComponents)).thenReturn(primaryKey);
  PersistenceContext actual=pmf.newContextForSliceQuery(CompleteBean.class,partitionComponents,EACH_QUORUM);
  assertThat(actual.stateHolderFacade.getEntity()).isNull();
  assertThat(actual.stateHolderFacade.getPrimaryKey()).isSameAs(primaryKey);
  assertThat(actual.stateHolderFacade.<CompleteBean>getEntityClass()).isSameAs(CompleteBean.class);
  assertThat(actual.stateHolderFacade.getEntityMeta()).isSameAs(meta);
  assertThat(actual.stateHolderFacade.getIdMeta()).isSameAs(idMeta);
  assertThat(actual.stateHolderFacade.getTtl().isPresent()).isFalse();
}
