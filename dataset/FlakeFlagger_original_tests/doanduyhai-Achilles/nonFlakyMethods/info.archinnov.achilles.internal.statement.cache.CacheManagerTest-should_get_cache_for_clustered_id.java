@Test public void should_get_cache_for_clustered_id() throws Exception {
  EntityMeta meta=new EntityMeta();
  meta.setTableName("table");
  PropertyMeta pm=PropertyMetaTestBuilder.valueClass(String.class).field("name").compNames("id","a","b").type(PropertyType.EMBEDDED_ID).build();
  when(context.<CompleteBean>getEntityClass()).thenReturn(CompleteBean.class);
  when(context.getEntityMeta()).thenReturn(meta);
  when(cache.getIfPresent(cacheKeyCaptor.capture())).thenReturn(ps);
  PreparedStatement actual=manager.getCacheForFieldSelect(session,cache,context,pm);
  assertThat(actual).isSameAs(ps);
  StatementCacheKey cacheKey=cacheKeyCaptor.getValue();
  assertThat(cacheKey.getFields()).containsOnly("id","a","b");
}
