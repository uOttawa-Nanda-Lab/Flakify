@Test public void should_init_persistence_context_with_entity() throws Exception {
  Object entity=new Object();
  Options options=OptionsBuilder.noOptions();
  PersistenceContext context=mock(PersistenceContext.class);
  PersistenceContext.PersistenceManagerFacade operations=mock(PersistenceContext.PersistenceManagerFacade.class);
  when(contextFactory.newContextWithFlushContext(entity,options,flushContext)).thenReturn(context);
  when(context.getPersistenceManagerFacade()).thenReturn(operations);
  PersistenceManagerOperations actual=manager.initPersistenceContext(entity,options);
  assertThat(actual).isSameAs(operations);
}
