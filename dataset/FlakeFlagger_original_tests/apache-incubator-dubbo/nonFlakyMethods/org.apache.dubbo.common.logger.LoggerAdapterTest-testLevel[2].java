@Test public void testLevel(){
  for (  Level targetLevel : Level.values()) {
    loggerAdapter.setLevel(targetLevel);
    assertThat(loggerAdapter.getLevel(),is(targetLevel));
  }
}
