@Test public void should_find() throws Exception {
  when(loader.load(context.entityFacade,CompleteBean.class)).thenReturn(entity);
  when(proxifier.buildProxyWithAllFieldsLoadedExceptCounters(entity,context.entityFacade)).thenReturn(entity);
  CompleteBean found=facade.find(CompleteBean.class);
  assertThat(found).isSameAs(entity);
  verify(flushContext).triggerInterceptor(meta,entity,POST_LOAD);
}
