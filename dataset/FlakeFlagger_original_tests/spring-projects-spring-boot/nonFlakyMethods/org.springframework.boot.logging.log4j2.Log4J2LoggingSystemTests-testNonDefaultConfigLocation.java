@Test public void testNonDefaultConfigLocation() throws Exception {
  this.loggingSystem.beforeInitialize();
  this.loggingSystem.initialize("classpath:log4j2-nondefault.xml",tmpDir() + "/tmp.log");
  this.logger.info("Hello world");
  String output=this.output.toString().trim();
  assertTrue("Wrong output:\n" + output,output.contains("Hello world"));
  assertTrue("Wrong output:\n" + output,output.contains(tmpDir() + "/tmp.log"));
  assertFalse(new File(tmpDir() + "/tmp.log").exists());
}
