@Test public void testLevelEnable(){
  assertThat(logger.isWarnEnabled(),not(nullValue()));
  assertThat(logger.isTraceEnabled(),not(nullValue()));
  assertThat(logger.isErrorEnabled(),not(nullValue()));
  assertThat(logger.isInfoEnabled(),not(nullValue()));
  assertThat(logger.isDebugEnabled(),not(nullValue()));
}
