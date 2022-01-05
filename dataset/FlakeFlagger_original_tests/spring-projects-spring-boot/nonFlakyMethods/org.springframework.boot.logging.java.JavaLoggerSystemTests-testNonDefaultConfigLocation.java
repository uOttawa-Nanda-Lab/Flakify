@Test public void testNonDefaultConfigLocation() throws Exception {
  this.loggingSystem.beforeInitialize();
  this.loggingSystem.initialize("classpath:logging-nondefault.properties",null);
  this.logger.info("Hello world");
  String output=this.output.toString().trim();
  assertTrue("Wrong output:\n" + output,output.contains("INFO: Hello"));
}
