@Test public void should_init_persistence_context_with_primary_key() throws Exception {
  Object primaryKey=new Object();
  Options options=OptionsBuilder.noOptions();
  PersistenceContext context=mock(PersistenceContext.class);
  PersistenceContext.PersistenceManagerFacade operations=mock(PersistenceContext.PersistenceManagerFacade.class);
  when(contextFactory.newContextWithFlushContext(Object.class,primaryKey,options,flushContext)).thenReturn(context);
  when(context.getPersistenceManagerFacade()).thenReturn(operations);
  PersistenceManagerOperations actual=manager.initPersistenceContext(Object.class,primaryKey,options);
  assertThat(actual).isSameAs(operations);
}
