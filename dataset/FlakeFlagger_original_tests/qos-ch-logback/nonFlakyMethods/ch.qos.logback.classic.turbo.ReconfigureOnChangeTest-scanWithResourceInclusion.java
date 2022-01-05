@Test(timeout=4000L) public void scanWithResourceInclusion() throws JoranException, IOException, InterruptedException {
  File topLevelFile=new File(INCLUSION_SCAN_TOP_BY_RESOURCE_AS_STR);
  File innerFile=new File(INCLUSION_SCAN_INNER1_AS_STR);
  configure(topLevelFile);
  List<File> fileList=getConfigurationFileList(loggerContext);
  assertThatListContainsFile(fileList,topLevelFile);
  assertThatListContainsFile(fileList,innerFile);
}
