@Test public void should_get_collection_and_map_operation_from_cache() throws Exception {
  EntityMeta meta=mock(EntityMeta.class);
  PropertyMeta pm=mock(PropertyMeta.class);
  DirtyCheckChangeSet changeSet=new DirtyCheckChangeSet(pm,ADD_TO_SET);
  when(context.<CompleteBean>getEntityClass()).thenReturn(CompleteBean.class);
  when(context.getEntityMeta()).thenReturn(meta);
  when(pm.getPropertyName()).thenReturn("property");
  StatementCacheKey cacheKey=new StatementCacheKey(CacheType.ADD_TO_SET,newHashSet("property"),CompleteBean.class,noOptions());
  when(cache.getIfPresent(cacheKey)).thenReturn(ps);
  final PreparedStatement actual=manager.getCacheForCollectionAndMapOperation(session,cache,context,pm,changeSet);
  assertThat(actual).isSameAs(ps);
  verify(cache,never()).put(cacheKey,ps);
  verifyZeroInteractions(generator);
}
