@Test public void setLevelToNull_LBCLASSIC_91(){
  loggerTest.setLevel(Level.DEBUG);
  ch.qos.logback.classic.Logger child=lc.getLogger(loggerTest.getName() + ".child");
  loggerTest.setLevel(null);
  assertEquals(root.getEffectiveLevel(),loggerTest.getEffectiveLevel());
  assertEquals(root.getEffectiveLevel(),child.getEffectiveLevel());
}
