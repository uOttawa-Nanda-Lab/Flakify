@Test public void should_generate_select_prepared_statement_when_not_found_in_cache() throws Exception {
  EntityMeta meta=new EntityMeta();
  meta.setTableName("table");
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).field("name").type(SIMPLE).build();
  when(context.<CompleteBean>getEntityClass()).thenReturn(CompleteBean.class);
  when(context.getEntityMeta()).thenReturn(meta);
  when(cache.getIfPresent(cacheKeyCaptor.capture())).thenReturn(null);
  when(generator.prepareSelectField(session,meta,pm)).thenReturn(ps);
  PreparedStatement actual=manager.getCacheForFieldSelect(session,cache,context,pm);
  assertThat(actual).isSameAs(ps);
  StatementCacheKey cacheKey=cacheKeyCaptor.getValue();
  verify(cache).put(cacheKey,ps);
}
