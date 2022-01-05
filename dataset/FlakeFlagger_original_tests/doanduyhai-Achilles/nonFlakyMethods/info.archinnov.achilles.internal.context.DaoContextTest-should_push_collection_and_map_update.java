@Test public void should_push_collection_and_map_update() throws Exception {
  PropertyMeta setMeta=PropertyMetaTestBuilder.valueClass(String.class).field("followers").build();
  when(changeSet.getChangeType()).thenReturn(ADD_TO_SET);
  when(changeSet.getPropertyMeta()).thenReturn(setMeta);
  when(cacheManager.getCacheForCollectionAndMapOperation(session,dynamicPSCache,context,setMeta,changeSet)).thenReturn(ps);
  when(binder.bindForCollectionAndMapUpdate(context,ps,changeSet)).thenReturn(bsWrapper);
  daoContext.pushCollectionAndMapUpdateStatement(context,changeSet);
  verify(context).pushStatement(bsWrapper);
}
