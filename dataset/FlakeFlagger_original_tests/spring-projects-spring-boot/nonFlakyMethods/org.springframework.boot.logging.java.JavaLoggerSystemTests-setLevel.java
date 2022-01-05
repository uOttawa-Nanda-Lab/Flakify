@Test public void setLevel() throws Exception {
  this.loggingSystem.beforeInitialize();
  this.loggingSystem.initialize(null,null);
  this.logger.debug("Hello");
  this.loggingSystem.setLogLevel("org.springframework.boot",LogLevel.DEBUG);
  this.logger.debug("Hello");
  assertThat(StringUtils.countOccurrencesOf(this.output.toString(),"Hello"),equalTo(1));
}
