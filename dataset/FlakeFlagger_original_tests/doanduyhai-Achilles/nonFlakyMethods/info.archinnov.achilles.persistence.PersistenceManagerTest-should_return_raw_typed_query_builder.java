@Test public void should_return_raw_typed_query_builder() throws Exception {
  when(entityMetaMap.containsKey(CompleteBean.class)).thenReturn(true);
  when(entityMetaMap.get(CompleteBean.class)).thenReturn(meta);
  when(meta.getPropertyMetas()).thenReturn(new HashMap<String,PropertyMeta>());
  TypedQuery<CompleteBean> builder=manager.rawTypedQuery(CompleteBean.class,"queryString");
  assertThat(builder).isNotNull();
  verify(typedQueryValidator).validateRawTypedQuery(CompleteBean.class,"queryString",meta);
  assertThat(Whitebox.getInternalState(builder,DaoContext.class)).isSameAs(daoContext);
  assertThat(Whitebox.getInternalState(builder,EntityMeta.class)).isSameAs(meta);
  assertThat(Whitebox.getInternalState(builder,PersistenceContextFactory.class)).isSameAs(contextFactory);
  assertThat(Whitebox.getInternalState(builder,String.class)).isEqualTo("querystring");
}
