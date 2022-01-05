@Test public void testCustomFormatter() throws Exception {
  this.loggingSystem.beforeInitialize();
  this.loggingSystem.initialize(null,null);
  this.logger.info("Hello world");
  String output=this.output.toString().trim();
  assertTrue("Wrong output:\n" + output,output.contains("Hello world"));
  assertTrue("Wrong output:\n" + output,output.contains("???? INFO ["));
}
