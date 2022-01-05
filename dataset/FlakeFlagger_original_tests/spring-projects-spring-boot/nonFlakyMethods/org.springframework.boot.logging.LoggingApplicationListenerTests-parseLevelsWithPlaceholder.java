@Test public void parseLevelsWithPlaceholder() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"foo=TRACE","logging.level.org.springframework.boot=${foo}");
  this.initializer.initialize(this.context.getEnvironment(),this.context.getClassLoader());
  this.logger.debug("testatdebug");
  this.logger.trace("testattrace");
  assertThat(this.outputCapture.toString(),containsString("testatdebug"));
  assertThat(this.outputCapture.toString(),containsString("testattrace"));
}
