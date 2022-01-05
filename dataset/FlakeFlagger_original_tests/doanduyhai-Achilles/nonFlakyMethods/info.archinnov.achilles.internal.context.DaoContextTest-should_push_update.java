@Test public void should_push_update() throws Exception {
  List<PropertyMeta> pms=new ArrayList<>();
  when(cacheManager.getCacheForFieldsUpdate(session,dynamicPSCache,context,pms)).thenReturn(ps);
  when(binder.bindForUpdate(context,ps,pms)).thenReturn(bsWrapper);
  daoContext.pushUpdateStatement(context,pms);
  verify(context).pushStatement(bsWrapper);
}
