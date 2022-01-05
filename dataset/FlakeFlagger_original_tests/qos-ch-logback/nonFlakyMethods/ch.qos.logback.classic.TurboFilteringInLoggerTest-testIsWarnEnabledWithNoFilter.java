@Test public void testIsWarnEnabledWithNoFilter(){
  addNoFilter();
  logger.setLevel(Level.DEBUG);
  assertFalse(logger.isWarnEnabled());
}
