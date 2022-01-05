@Test public void should_refresh() throws Exception {
  CompleteBean bean=CompleteBeanTestBuilder.builder().id(12L).buid();
  when(context.<CompleteBean>getEntityClass()).thenReturn(CompleteBean.class);
  when(context.getPrimaryKey()).thenReturn(bean.getId());
  when(context.getEntity()).thenReturn(bean);
  when(proxifier.getInterceptor(bean)).thenReturn(jpaEntityInterceptor);
  when(jpaEntityInterceptor.getTarget()).thenReturn(bean);
  when(jpaEntityInterceptor.getDirtyMap()).thenReturn(dirtyMap);
  when(jpaEntityInterceptor.getAlreadyLoaded()).thenReturn(alreadyLoaded);
  when(context.getEntityMeta()).thenReturn(entityMeta);
  when(loader.load(context,CompleteBean.class)).thenReturn(bean);
  when(context.getAllGettersExceptCounters()).thenReturn(allGettersExceptCounters);
  refresher.refresh(bean,context);
  verify(dirtyMap).clear();
  verify(alreadyLoaded).clear();
  verify(alreadyLoaded).addAll(allGettersExceptCounters);
  verify(jpaEntityInterceptor).setTarget(bean);
}
