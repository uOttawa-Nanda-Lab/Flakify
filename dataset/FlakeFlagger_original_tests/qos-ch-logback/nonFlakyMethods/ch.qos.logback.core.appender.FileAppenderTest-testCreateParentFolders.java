@Test public void testCreateParentFolders(){
  String filename=CoreTestConstants.OUTPUT_DIR_PREFIX + "/fat-testCreateParentFolders-" + diff+ "/testCreateParentFolders.txt";
  File file=new File(filename);
  FileAppender<Object> appender=new FileAppender<Object>();
  appender.setEncoder(new DummyEncoder<Object>());
  appender.setAppend(false);
  appender.setFile(filename);
  appender.setName("testCreateParentFolders");
  appender.setContext(context);
  appender.start();
  appender.doAppend(new Object());
  appender.stop();
  assertFalse(FileUtil.isParentDirectoryCreationRequired(file));
  assertTrue(file.exists());
  assertTrue("failed to delete " + file.getAbsolutePath(),file.delete());
  File parent=file.getParentFile();
  assertTrue("failed to delete " + parent.getAbsolutePath(),parent.delete());
}
