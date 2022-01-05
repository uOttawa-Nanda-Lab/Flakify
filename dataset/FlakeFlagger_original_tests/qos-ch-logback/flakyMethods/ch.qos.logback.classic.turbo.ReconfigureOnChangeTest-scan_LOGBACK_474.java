@Test(timeout=4000L) public void scan_LOGBACK_474() throws JoranException, IOException, InterruptedException {
  File file=new File(SCAN_LOGBACK_474_FILE_AS_STR);
  configure(file);
  RunnableWithCounterAndDone[] runnableArray=buildRunnableArray(file,UpdateType.TOUCH);
  harness.execute(runnableArray);
  loggerContext.getStatusManager().add(new InfoStatus("end of execution ",this));
  verify(expectedResets);
}
