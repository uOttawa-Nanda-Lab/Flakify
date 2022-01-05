@Test public void renameParentDirectoryTest() throws IOException {
  StringBuilder toCompare=new StringBuilder();
  mFsShell.mkdir(new String[]{"mkdir","/test/File1"});
  toCompare.append(getCommandOutput(new String[]{"mkdir","/test/File1"}));
  mFsShell.rename(new String[]{"rename","/test","/test2"});
  toCompare.append(getCommandOutput(new String[]{"mv","/test","/test2"}));
  Assert.assertNotNull(mTfs.getFile("/test2/File1"));
  Assert.assertNull(mTfs.getFile("/test"));
  Assert.assertNull(mTfs.getFile("/test/File1"));
  Assert.assertEquals(toCompare.toString(),mOutput.toString());
}
