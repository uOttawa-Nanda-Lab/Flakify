@Test public void parseLevelsFails() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"logging.level.org.springframework.boot=GARBAGE");
  this.initializer.initialize(this.context.getEnvironment(),this.context.getClassLoader());
  this.logger.debug("testatdebug");
  assertThat(this.outputCapture.toString(),not(containsString("testatdebug")));
  assertThat(this.outputCapture.toString(),containsString("Cannot set level: GARBAGE"));
}
