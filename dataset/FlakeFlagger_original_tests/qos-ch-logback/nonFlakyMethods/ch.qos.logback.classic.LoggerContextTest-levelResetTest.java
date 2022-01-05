@Test public void levelResetTest(){
  Logger root=lc.getLogger(Logger.ROOT_LOGGER_NAME);
  root.setLevel(Level.TRACE);
  assertTrue(root.isTraceEnabled());
  lc.reset();
  assertFalse(root.isTraceEnabled());
  assertTrue(root.isDebugEnabled());
}
