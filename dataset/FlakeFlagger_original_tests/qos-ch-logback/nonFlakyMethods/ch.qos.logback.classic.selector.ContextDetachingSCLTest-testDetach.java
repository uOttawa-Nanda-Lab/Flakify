@Test public void testDetach(){
  ContextJNDISelector selector=(ContextJNDISelector)ContextSelectorStaticBinder.getSingleton().getContextSelector();
  contextDetachingSCL.contextDestroyed(null);
  assertEquals(0,selector.getCount());
}
