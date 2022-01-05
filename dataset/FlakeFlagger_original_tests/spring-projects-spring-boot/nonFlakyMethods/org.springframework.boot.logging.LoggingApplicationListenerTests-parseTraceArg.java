@Test public void parseTraceArg() throws Exception {
  EnvironmentTestUtils.addEnvironment(this.context,"trace");
  this.initializer.initialize(this.context.getEnvironment(),this.context.getClassLoader());
  this.logger.debug("testatdebug");
  this.logger.trace("testattrace");
  assertThat(this.outputCapture.toString(),containsString("testatdebug"));
  assertThat(this.outputCapture.toString(),containsString("testattrace"));
}
