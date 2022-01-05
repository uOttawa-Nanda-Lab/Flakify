@Test public void parseArgsDisabled() throws Exception {
  this.initializer.setParseArgs(false);
  EnvironmentTestUtils.addEnvironment(this.context,"debug");
  this.initializer.initialize(this.context.getEnvironment(),this.context.getClassLoader());
  this.logger.debug("testatdebug");
  assertThat(this.outputCapture.toString(),not(containsString("testatdebug")));
}
