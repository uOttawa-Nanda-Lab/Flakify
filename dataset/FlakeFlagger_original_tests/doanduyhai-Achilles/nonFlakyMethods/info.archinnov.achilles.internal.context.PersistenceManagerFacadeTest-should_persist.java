@Test public void should_persist() throws Exception {
  Object entity=new Object();
  context.entity=entity;
  when(proxifier.buildProxyWithAllFieldsLoadedExceptCounters(entity,context.entityFacade)).thenReturn(entity);
  Object actual=facade.persist(entity);
  assertThat(actual).isSameAs(entity);
  InOrder inOrder=Mockito.inOrder(flushContext,persister);
  inOrder.verify(flushContext).triggerInterceptor(meta,entity,PRE_PERSIST);
  inOrder.verify(persister).persist(context.entityFacade);
  inOrder.verify(flushContext).flush();
  inOrder.verify(flushContext).triggerInterceptor(meta,entity,POST_PERSIST);
}
