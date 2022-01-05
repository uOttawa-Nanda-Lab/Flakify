@Test public void setLevelToNull_B(){
  loggerTest.setLevel(Level.DEBUG);
  loggerTest.setLevel(null);
  assertEquals(root.getEffectiveLevel(),loggerTest.getEffectiveLevel());
}
