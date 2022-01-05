@Test public void testLoggingContextReset(){
  addYesFilter();
  assertNotNull(context.getTurboFilterList().get(0));
  context.reset();
  assertEquals(0,context.getTurboFilterList().size());
}
