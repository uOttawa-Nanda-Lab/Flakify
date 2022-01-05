@Test public void should_get_cache_for_simple_field() throws Exception {
  EntityMeta meta=new EntityMeta();
  meta.setTableName("table");
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).field("name").type(SIMPLE).build();
  when(context.<CompleteBean>getEntityClass()).thenReturn(CompleteBean.class);
  when(context.getEntityMeta()).thenReturn(meta);
  when(cache.getIfPresent(cacheKeyCaptor.capture())).thenReturn(ps);
  PreparedStatement actual=manager.getCacheForFieldSelect(session,cache,context,pm);
  assertThat(actual).isSameAs(ps);
  StatementCacheKey cacheKey=cacheKeyCaptor.getValue();
  assertThat(cacheKey.<CompleteBean>getEntityClass()).isSameAs(CompleteBean.class);
  assertThat(cacheKey.getType()).isEqualTo(CacheType.SELECT_FIELD);
  assertThat(cacheKey.getFields()).containsExactly("name");
}
