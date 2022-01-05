@Test public void overrideConfigLocation(){
  EnvironmentTestUtils.addEnvironment(this.context,"logging.config: classpath:logback-nondefault.xml");
  this.initializer.initialize(this.context.getEnvironment(),this.context.getClassLoader());
  this.logger.info("Hello world");
  String output=this.outputCapture.toString().trim();
  assertTrue("Wrong output:\n" + output,output.contains("Hello world"));
  assertFalse("Wrong output:\n" + output,output.contains("???"));
  assertTrue("Wrong output:\n" + output,output.startsWith("LOG_FILE_IS_UNDEFINED"));
  assertTrue("Wrong output:\n" + output,output.endsWith("BOOTBOOT"));
}
