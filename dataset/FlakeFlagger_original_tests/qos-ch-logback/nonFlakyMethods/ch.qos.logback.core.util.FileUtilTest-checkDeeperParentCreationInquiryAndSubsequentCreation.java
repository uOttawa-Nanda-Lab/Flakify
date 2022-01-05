@Test public void checkDeeperParentCreationInquiryAndSubsequentCreation(){
  File file=new File(CoreTestConstants.OUTPUT_DIR_PREFIX + "/fu" + diff+ "/bla/testing.txt");
  cleanupList.add(file);
  cleanupList.add(file.getParentFile());
  cleanupList.add(file.getParentFile().getParentFile());
  assertTrue(FileUtil.isParentDirectoryCreationRequired(file));
  assertTrue(FileUtil.createMissingParentDirectories(file));
  assertFalse(FileUtil.isParentDirectoryCreationRequired(file));
}
