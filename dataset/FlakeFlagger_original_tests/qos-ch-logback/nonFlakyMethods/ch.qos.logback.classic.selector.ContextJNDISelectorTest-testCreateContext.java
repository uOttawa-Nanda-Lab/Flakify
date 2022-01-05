@Test public void testCreateContext(){
  MockInitialContext mic=MockInitialContextFactory.getContext();
  mic.map.put(ClassicConstants.JNDI_CONTEXT_NAME,"tata");
  LoggerFactory.getLogger(ContextDetachingSCLTest.class);
  ContextJNDISelector selector=(ContextJNDISelector)ContextSelectorStaticBinder.getSingleton().getContextSelector();
  Context context=selector.getLoggerContext();
  assertEquals("tata",context.getName());
  System.out.println(selector.getContextNames());
  assertEquals(2,selector.getCount());
}
