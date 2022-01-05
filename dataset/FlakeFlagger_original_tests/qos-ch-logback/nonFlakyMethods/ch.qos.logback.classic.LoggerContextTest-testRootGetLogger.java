@Test public void testRootGetLogger(){
  Logger root=lc.getLogger(Logger.ROOT_LOGGER_NAME);
  assertEquals(Level.DEBUG,root.getLevel());
  assertEquals(Level.DEBUG,root.getEffectiveLevel());
}
