@Test public void testEnabledX_Warn() throws Exception {
  root.setLevel(Level.WARN);
  checkLevelThreshold(loggerTest,Level.WARN);
}
