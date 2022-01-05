@Test public void parseLevels() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"logging.level.org.springframework.boot=TRACE");
  this.initializer.initialize(this.context.getEnvironment(),this.context.getClassLoader());
  this.logger.debug("testatdebug");
  this.logger.trace("testattrace");
  assertThat(this.outputCapture.toString(),containsString("testatdebug"));
  assertThat(this.outputCapture.toString(),containsString("testattrace"));
}
