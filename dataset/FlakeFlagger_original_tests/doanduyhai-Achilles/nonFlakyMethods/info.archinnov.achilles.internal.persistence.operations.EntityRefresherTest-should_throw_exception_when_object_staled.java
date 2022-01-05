@Test(expected=AchillesStaleObjectStateException.class) public void should_throw_exception_when_object_staled() throws Exception {
  CompleteBean bean=CompleteBeanTestBuilder.builder().id(12L).buid();
  when(context.<CompleteBean>getEntityClass()).thenReturn(CompleteBean.class);
  when(context.getPrimaryKey()).thenReturn(bean.getId());
  when(context.getEntity()).thenReturn(bean);
  when(proxifier.getInterceptor(bean)).thenReturn(jpaEntityInterceptor);
  when(jpaEntityInterceptor.getTarget()).thenReturn(bean);
  when(jpaEntityInterceptor.getDirtyMap()).thenReturn(dirtyMap);
  when(context.getEntityMeta()).thenReturn(entityMeta);
  when(loader.load(context,CompleteBean.class)).thenReturn(null);
  refresher.refresh(bean,context);
}
