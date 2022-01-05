@Test public void baseConfigLocation(){
  this.initializer.initialize(this.context.getEnvironment(),this.context.getClassLoader());
  this.logger.info("Hello world");
  String output=this.outputCapture.toString().trim();
  assertTrue("Wrong output:\n" + output,output.contains("Hello world"));
  assertFalse("Wrong output:\n" + output,output.contains("???"));
  assertFalse(new File(tmpDir() + "/spring.log").exists());
}
