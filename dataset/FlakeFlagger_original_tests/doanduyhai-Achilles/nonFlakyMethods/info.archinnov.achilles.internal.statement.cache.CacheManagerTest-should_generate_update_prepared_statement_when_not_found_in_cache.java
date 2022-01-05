@Test public void should_generate_update_prepared_statement_when_not_found_in_cache() throws Exception {
  EntityMeta meta=new EntityMeta();
  meta.setTableName("table");
  PropertyMeta nameMeta=completeBean(Void.class,String.class).field("name").type(SIMPLE).build();
  PropertyMeta ageMeta=completeBean(Void.class,String.class).field("age").type(SIMPLE).build();
  List<PropertyMeta> pms=asList(nameMeta,ageMeta);
  when(context.<CompleteBean>getEntityClass()).thenReturn(CompleteBean.class);
  when(context.getEntityMeta()).thenReturn(meta);
  when(cache.getIfPresent(cacheKeyCaptor.capture())).thenReturn(null);
  when(generator.prepareUpdateFields(session,meta,pms,noOptions())).thenReturn(ps);
  PreparedStatement actual=manager.getCacheForFieldsUpdate(session,cache,context,pms);
  assertThat(actual).isSameAs(ps);
  StatementCacheKey cacheKey=cacheKeyCaptor.getValue();
  assertThat(cacheKey.<CompleteBean>getEntityClass()).isSameAs(CompleteBean.class);
  assertThat(cacheKey.getType()).isEqualTo(CacheType.UPDATE_FIELDS);
  assertThat(cacheKey.getFields()).containsOnly("name","age");
}
