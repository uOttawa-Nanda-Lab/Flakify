@Test public void testGetLogger(){
  Logger logger1=LoggerFactory.getLogger(this.getClass());
  Logger logger2=LoggerFactory.getLogger(this.getClass());
  assertThat(logger1,is(logger2));
}
