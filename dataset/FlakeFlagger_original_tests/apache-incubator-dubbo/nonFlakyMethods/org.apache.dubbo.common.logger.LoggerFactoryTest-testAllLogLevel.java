@Test public void testAllLogLevel(){
  for (  Level targetLevel : Level.values()) {
    LoggerFactory.setLevel(targetLevel);
    Level level=LoggerFactory.getLevel();
    assertThat(level,is(targetLevel));
  }
}
