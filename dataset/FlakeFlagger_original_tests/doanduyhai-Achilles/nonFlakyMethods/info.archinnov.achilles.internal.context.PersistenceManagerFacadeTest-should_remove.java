@Test public void should_remove() throws Exception {
  Object entity=new Object();
  context.entity=entity;
  facade.remove();
  InOrder inOrder=Mockito.inOrder(flushContext,persister);
  inOrder.verify(flushContext).triggerInterceptor(meta,entity,PRE_REMOVE);
  inOrder.verify(persister).remove(context.entityFacade);
  inOrder.verify(flushContext).flush();
  inOrder.verify(flushContext).triggerInterceptor(meta,entity,POST_REMOVE);
}
