@Test public void addLogFilePropertyWithDefault(){
  assertFalse(new File("target/foo.log").exists());
  EnvironmentTestUtils.addEnvironment(this.context,"logging.file: target/foo.log");
  this.initializer.initialize(this.context.getEnvironment(),this.context.getClassLoader());
  Log logger=LogFactory.getLog(LoggingApplicationListenerTests.class);
  logger.info("Hello world");
  assertTrue(new File("target/foo.log").exists());
}
