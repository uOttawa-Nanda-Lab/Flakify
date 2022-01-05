@Test public void setLevelToNull_A(){
  loggerTest.setLevel(null);
  assertEquals(root.getEffectiveLevel(),loggerTest.getEffectiveLevel());
}
