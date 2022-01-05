@Test public void should_return_slice_query_builder() throws Exception {
  when(entityMetaMap.get(CompleteBean.class)).thenReturn(meta);
  when(meta.isClusteredEntity()).thenReturn(true);
  SliceQueryBuilder<CompleteBean> builder=manager.sliceQuery(CompleteBean.class);
  assertThat(Whitebox.getInternalState(builder,SliceQueryExecutor.class)).isSameAs(sliceQueryExecutor);
  assertThat(Whitebox.getInternalState(builder,EntityMeta.class)).isSameAs(meta);
  assertThat(Whitebox.getInternalState(builder,PropertyMeta.class)).isSameAs(idMeta);
}
