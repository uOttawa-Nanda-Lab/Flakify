@Test public void testGetLogger(){
  Logger logger=loggerAdapter.getLogger(this.getClass());
  assertThat(logger.getClass().isAssignableFrom(this.loggerClass),is(true));
  logger=loggerAdapter.getLogger(this.getClass().getSimpleName());
  assertThat(logger.getClass().isAssignableFrom(this.loggerClass),is(true));
}
