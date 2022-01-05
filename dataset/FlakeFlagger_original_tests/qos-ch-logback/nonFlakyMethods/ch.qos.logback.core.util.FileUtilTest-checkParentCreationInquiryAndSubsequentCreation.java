@Test public void checkParentCreationInquiryAndSubsequentCreation(){
  File file=new File(CoreTestConstants.OUTPUT_DIR_PREFIX + "/fu" + diff+ "/testing.txt");
  cleanupList.add(file);
  cleanupList.add(file.getParentFile());
  assertTrue(FileUtil.isParentDirectoryCreationRequired(file));
  assertTrue(FileUtil.createMissingParentDirectories(file));
  assertFalse(FileUtil.isParentDirectoryCreationRequired(file));
}
