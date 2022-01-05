@Test(timeout=4000L) public void scanWithFileInclusion() throws JoranException, IOException, InterruptedException {
  File topLevelFile=new File(INCLUSION_SCAN_TOPLEVEL0_AS_STR);
  File innerFile=new File(INCLUSION_SCAN_INNER0_AS_STR);
  configure(topLevelFile);
  List<File> fileList=getConfigurationFileList(loggerContext);
  assertThatListContainsFile(fileList,topLevelFile);
  assertThatListContainsFile(fileList,innerFile);
}
