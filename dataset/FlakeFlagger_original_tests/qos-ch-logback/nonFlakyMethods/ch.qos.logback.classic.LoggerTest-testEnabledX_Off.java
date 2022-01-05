@Test public void testEnabledX_Off() throws Exception {
  root.setLevel(Level.OFF);
  checkLevelThreshold(loggerTest,Level.OFF);
}
