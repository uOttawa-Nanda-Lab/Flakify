@Test public void gafferInstallFilter() throws JoranException, IOException, InterruptedException {
  File file=new File(G_SCAN1_FILE_AS_STR);
  gConfigure(file);
  List<File> fileList=getConfigurationFileList(loggerContext);
  assertThatListContainsFile(fileList,file);
  assertThatFirstFilterIsROCF();
  rocfDetachReconfigurationToNewThreadAndAwaitTermination();
  fileList=getConfigurationFileList(loggerContext);
  assertThatListContainsFile(fileList,file);
  assertThatFirstFilterIsROCF();
  assertEquals(2,checker.matchCount("Will scan for changes in"));
}
