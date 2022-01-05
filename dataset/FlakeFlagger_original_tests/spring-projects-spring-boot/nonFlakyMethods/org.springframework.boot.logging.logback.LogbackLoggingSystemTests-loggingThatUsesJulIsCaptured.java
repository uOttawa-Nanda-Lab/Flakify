@Test public void loggingThatUsesJulIsCaptured(){
  this.loggingSystem.beforeInitialize();
  this.loggingSystem.initialize(null,null);
  java.util.logging.Logger julLogger=java.util.logging.Logger.getLogger(getClass().getName());
  julLogger.info("Hello world");
  String output=this.output.toString().trim();
  assertTrue("Wrong output:\n" + output,output.contains("Hello world"));
}
