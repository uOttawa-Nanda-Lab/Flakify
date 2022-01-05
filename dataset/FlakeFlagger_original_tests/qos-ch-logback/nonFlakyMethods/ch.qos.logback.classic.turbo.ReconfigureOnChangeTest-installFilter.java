@Test public void installFilter() throws JoranException, IOException, InterruptedException {
  File file=new File(SCAN1_FILE_AS_STR);
  configure(file);
  List<File> fileList=getConfigurationFileList(loggerContext);
  assertThatListContainsFile(fileList,file);
  assertThatFirstFilterIsROCF();
  StatusPrinter.print(loggerContext);
}
