@Test public void should_refresh() throws Exception {
  facade.refresh(entity);
  InOrder inOrder=Mockito.inOrder(flushContext,refresher);
  inOrder.verify(refresher).refresh(entity,context.entityFacade);
  inOrder.verify(flushContext).triggerInterceptor(meta,context.entity,POST_LOAD);
}
