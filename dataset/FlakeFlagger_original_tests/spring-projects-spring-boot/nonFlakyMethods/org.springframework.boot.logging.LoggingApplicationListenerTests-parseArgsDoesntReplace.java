@Test public void parseArgsDoesntReplace() throws Exception {
  this.initializer.setSpringBootLogging(LogLevel.ERROR);
  this.initializer.setParseArgs(false);
  this.initializer.onApplicationEvent(new ApplicationStartedEvent(this.springApplication,new String[]{"--debug"}));
  this.initializer.initialize(this.context.getEnvironment(),this.context.getClassLoader());
  this.logger.debug("testatdebug");
  assertThat(this.outputCapture.toString(),not(containsString("testatdebug")));
}
