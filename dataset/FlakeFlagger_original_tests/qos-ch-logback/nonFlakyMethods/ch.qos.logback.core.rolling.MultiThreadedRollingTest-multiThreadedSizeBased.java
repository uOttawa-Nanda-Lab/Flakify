@Test public void multiThreadedSizeBased() throws InterruptedException, IOException {
  setUpSizeBasedTriggeringPolicy(rfa);
  executeHarness(TOTAL_DURATION,true);
  int numFiles=testFileCount();
  printScriptForSizeBased(numFiles);
  verify();
}
