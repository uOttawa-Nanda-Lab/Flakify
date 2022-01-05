@Test public void parseDebugArg() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"debug");
  this.initializer.initialize(this.context.getEnvironment(),this.context.getClassLoader());
  this.logger.debug("testatdebug");
  this.logger.trace("testattrace");
  assertThat(this.outputCapture.toString(),containsString("testatdebug"));
  assertThat(this.outputCapture.toString(),not(containsString("testattrace")));
}
