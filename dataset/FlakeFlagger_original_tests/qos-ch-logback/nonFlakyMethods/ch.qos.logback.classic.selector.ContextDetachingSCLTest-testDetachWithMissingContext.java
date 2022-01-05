@Test public void testDetachWithMissingContext(){
  MockInitialContext mic=MockInitialContextFactory.getContext();
  mic.map.put(ClassicConstants.JNDI_CONTEXT_NAME,"tata");
  ContextJNDISelector selector=(ContextJNDISelector)ContextSelectorStaticBinder.getSingleton().getContextSelector();
  assertEquals("tata",selector.getLoggerContext().getName());
  mic.map.put(ClassicConstants.JNDI_CONTEXT_NAME,"titi");
  assertEquals("titi",selector.getLoggerContext().getName());
  contextDetachingSCL.contextDestroyed(null);
  assertEquals(2,selector.getCount());
}
