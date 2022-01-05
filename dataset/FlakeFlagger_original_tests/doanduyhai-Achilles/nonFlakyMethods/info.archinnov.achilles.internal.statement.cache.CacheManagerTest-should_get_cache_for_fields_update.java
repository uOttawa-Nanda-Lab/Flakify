@Test public void should_get_cache_for_fields_update() throws Exception {
  EntityMeta meta=new EntityMeta();
  meta.setTableName("table");
  PropertyMeta nameMeta=completeBean(Void.class,String.class).field("name").type(SIMPLE).build();
  PropertyMeta ageMeta=completeBean(Void.class,String.class).field("age").type(SIMPLE).build();
  when(context.<CompleteBean>getEntityClass()).thenReturn(CompleteBean.class);
  when(context.getEntityMeta()).thenReturn(meta);
  when(cache.getIfPresent(cacheKeyCaptor.capture())).thenReturn(ps);
  PreparedStatement actual=manager.getCacheForFieldsUpdate(session,cache,context,asList(nameMeta,ageMeta));
  assertThat(actual).isSameAs(ps);
  StatementCacheKey cacheKey=cacheKeyCaptor.getValue();
  assertThat(cacheKey.<CompleteBean>getEntityClass()).isSameAs(CompleteBean.class);
  assertThat(cacheKey.getType()).isEqualTo(CacheType.UPDATE_FIELDS);
  assertThat(cacheKey.getFields()).containsOnly("name","age");
}
