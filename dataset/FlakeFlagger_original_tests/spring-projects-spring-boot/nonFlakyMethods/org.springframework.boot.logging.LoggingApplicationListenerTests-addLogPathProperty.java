@Test public void addLogPathProperty(){
  EnvironmentTestUtils.addEnvironment(this.context,"logging.config: classpath:logback-nondefault.xml","logging.path: target/foo/");
  this.initializer.initialize(this.context.getEnvironment(),this.context.getClassLoader());
  Log logger=LogFactory.getLog(LoggingApplicationListenerTests.class);
  logger.info("Hello world");
  String output=this.outputCapture.toString().trim();
  assertTrue("Wrong output:\n" + output,output.startsWith("target/foo/spring.log"));
}
