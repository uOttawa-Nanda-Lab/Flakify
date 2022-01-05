@Test public void withFile() throws Exception {
  this.loggingSystem.beforeInitialize();
  this.logger.info("Hidden");
  this.loggingSystem.initialize(null,tmpDir() + "/spring.log");
  this.logger.info("Hello world");
  String output=this.output.toString().trim();
  assertTrue("Wrong output:\n" + output,output.contains("Hello world"));
  assertFalse("Output not hidden:\n" + output,output.contains("Hidden"));
  assertTrue(new File(tmpDir() + "/spring.log").exists());
}
