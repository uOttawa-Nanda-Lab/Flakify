@Test public void testSystemPropertyInitializesFormat() throws Exception {
  System.setProperty("PID","1234");
  this.loggingSystem.beforeInitialize();
  this.loggingSystem.initialize("classpath:" + ClassUtils.addResourcePathToPackagePath(getClass(),"logging.properties"),null);
  this.logger.info("Hello world");
  this.logger.info("Hello world");
  String output=this.output.toString().trim();
  assertTrue("Wrong output:\n" + output,output.contains("Hello world"));
  assertTrue("Wrong output:\n" + output,output.contains("1234 INFO ["));
}
