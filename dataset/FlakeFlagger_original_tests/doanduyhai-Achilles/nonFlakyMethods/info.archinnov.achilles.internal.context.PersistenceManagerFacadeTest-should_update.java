@Test public void should_update() throws Exception {
  final CompleteBean rawEntity=new CompleteBean();
  context.entity=rawEntity;
  facade.update(entity);
  InOrder inOrder=Mockito.inOrder(flushContext,updater);
  inOrder.verify(flushContext).triggerInterceptor(meta,rawEntity,PRE_UPDATE);
  inOrder.verify(updater).update(context.entityFacade,entity);
  inOrder.verify(flushContext).flush();
  inOrder.verify(flushContext).triggerInterceptor(meta,rawEntity,POST_UPDATE);
}
