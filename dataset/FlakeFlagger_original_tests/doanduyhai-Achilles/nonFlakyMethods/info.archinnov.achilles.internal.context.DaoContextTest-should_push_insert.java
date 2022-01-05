@Test public void should_push_insert() throws Exception {
  entityMeta.setConsistencyLevels(Pair.create(ONE,ALL));
  List<PropertyMeta> pms=new ArrayList<>();
  when(cacheManager.getCacheForEntityInsert(session,dynamicPSCache,context,pms)).thenReturn(ps);
  when(binder.bindForInsert(context,ps,pms)).thenReturn(bsWrapper);
  when(context.getConsistencyLevel()).thenReturn(Optional.<ConsistencyLevel>fromNullable(null));
  daoContext.pushInsertStatement(context,pms);
  verify(context).pushStatement(bsWrapper);
}
