@Test public void testLoggerLevel(){
  LoggerFactory.setLevel(Level.INFO);
  Level level=LoggerFactory.getLevel();
  assertThat(level,is(Level.INFO));
}
