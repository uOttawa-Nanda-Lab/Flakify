@Test public void defaultContext(){
  MockInitialContext mic=MockInitialContextFactory.getContext();
  mic.map.put(ClassicConstants.JNDI_CONTEXT_NAME,null);
  ContextJNDISelector selector=(ContextJNDISelector)ContextSelectorStaticBinder.getSingleton().getContextSelector();
  Context context=selector.getLoggerContext();
  assertEquals("default",context.getName());
}
