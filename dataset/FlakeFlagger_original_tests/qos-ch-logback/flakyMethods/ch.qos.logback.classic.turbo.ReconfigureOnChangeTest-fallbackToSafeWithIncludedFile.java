@Test(timeout=4000L) public void fallbackToSafeWithIncludedFile() throws IOException, JoranException, InterruptedException {
  String topLevelFileAsStr=CoreTestConstants.OUTPUT_DIR_PREFIX + "reconfigureOnChangeConfig_top-" + diff+ ".xml";
  String innerFileAsStr=CoreTestConstants.OUTPUT_DIR_PREFIX + "reconfigureOnChangeConfig_inner-" + diff+ ".xml";
  File topLevelFile=new File(topLevelFileAsStr);
  writeToFile(topLevelFile,"<configuration scan=\"true\" scanPeriod=\"50 millisecond\"><include file=\"" + innerFileAsStr + "\"/></configuration> ");
  File innerFile=new File(innerFileAsStr);
  writeToFile(innerFile,"<included><root level=\"ERROR\"/></included> ");
  configure(topLevelFile);
  writeToFile(innerFile,"<included>\n<root>\n</included>");
  rocfDetachReconfigurationToNewThreadAndAwaitTermination();
  checker.assertContainsMatch(Status.WARN,"Falling back to previously registered safe configuration.");
  checker.assertContainsMatch(Status.INFO,"Re-registering previous fallback configuration once more");
  assertThatFirstFilterIsROCF();
}
