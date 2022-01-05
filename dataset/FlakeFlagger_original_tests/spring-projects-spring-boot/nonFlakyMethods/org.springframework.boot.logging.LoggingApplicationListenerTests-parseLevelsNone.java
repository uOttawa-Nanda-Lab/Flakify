@Test public void parseLevelsNone() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"logging.level.org.springframework.boot=OFF");
  this.initializer.initialize(this.context.getEnvironment(),this.context.getClassLoader());
  this.logger.debug("testatdebug");
  this.logger.fatal("testatfatal");
  assertThat(this.outputCapture.toString(),not(containsString("testatdebug")));
  assertThat(this.outputCapture.toString(),not(containsString("testatfatal")));
}
