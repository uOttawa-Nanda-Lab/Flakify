@Test public void withFile() throws Exception {
  File temp=new File(tmpDir());
  File[] logFiles=temp.listFiles(SPRING_LOG_FILTER);
  for (  File file : logFiles) {
    file.delete();
  }
  this.loggingSystem.beforeInitialize();
  this.logger.info("Hidden");
  this.loggingSystem.initialize(null,tmpDir() + "/spring.log");
  this.logger.info("Hello world");
  String output=this.output.toString().trim();
  assertTrue("Wrong output:\n" + output,output.contains("Hello world"));
  assertFalse("Output not hidden:\n" + output,output.contains("Hidden"));
  assertThat(temp.listFiles(SPRING_LOG_FILTER).length,greaterThan(0));
}
