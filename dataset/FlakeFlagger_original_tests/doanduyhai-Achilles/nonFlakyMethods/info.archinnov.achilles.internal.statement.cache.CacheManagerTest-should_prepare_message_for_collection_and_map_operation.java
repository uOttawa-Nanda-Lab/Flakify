@Test public void should_prepare_message_for_collection_and_map_operation() throws Exception {
  EntityMeta meta=mock(EntityMeta.class);
  PropertyMeta pm=mock(PropertyMeta.class);
  DirtyCheckChangeSet changeSet=new DirtyCheckChangeSet(pm,ADD_TO_SET);
  when(context.<CompleteBean>getEntityClass()).thenReturn(CompleteBean.class);
  when(context.getEntityMeta()).thenReturn(meta);
  when(pm.getPropertyName()).thenReturn("property");
  StatementCacheKey cacheKey=new StatementCacheKey(CacheType.ADD_TO_SET,newHashSet("property"),CompleteBean.class,noOptions());
  when(cache.getIfPresent(cacheKey)).thenReturn(null);
  when(generator.prepareCollectionAndMapUpdate(session,meta,changeSet,noOptions())).thenReturn(ps);
  final PreparedStatement actual=manager.getCacheForCollectionAndMapOperation(session,cache,context,pm,changeSet);
  assertThat(actual).isSameAs(ps);
  verify(cache).put(cacheKey,ps);
}
