@Test public void testGetExistingContext(){
  ContextSelector selector=ContextSelectorStaticBinder.getSingleton().getContextSelector();
  Context context=selector.getLoggerContext();
  assertEquals("toto",context.getName());
}
