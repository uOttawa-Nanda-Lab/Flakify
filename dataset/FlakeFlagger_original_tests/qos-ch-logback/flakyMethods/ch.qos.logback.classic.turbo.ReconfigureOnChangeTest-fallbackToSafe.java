@Test(timeout=4000L) public void fallbackToSafe() throws IOException, JoranException, InterruptedException {
  String path=CoreTestConstants.OUTPUT_DIR_PREFIX + "reconfigureOnChangeConfig_fallbackToSafe-" + diff+ ".xml";
  File topLevelFile=new File(path);
  writeToFile(topLevelFile,"<configuration scan=\"true\" scanPeriod=\"50 millisecond\"><root level=\"ERROR\"/></configuration> ");
  configure(topLevelFile);
  writeToFile(topLevelFile,"<configuration scan=\"true\" scanPeriod=\"50 millisecond\">\n" + "  <root></configuration>");
  rocfDetachReconfigurationToNewThreadAndAwaitTermination();
  checker.assertContainsMatch(Status.WARN,"Falling back to previously registered safe configuration.");
  checker.assertContainsMatch(Status.INFO,"Re-registering previous fallback configuration once more");
  assertThatFirstFilterIsROCF();
}
