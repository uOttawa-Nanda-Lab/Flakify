@Test public void renameTest() throws IOException {
  StringBuilder toCompare=new StringBuilder();
  mFsShell.mkdir(new String[]{"mkdir","/testFolder1"});
  toCompare.append(getCommandOutput(new String[]{"mkdir","/testFolder1"}));
  Assert.assertNotNull(mTfs.getFile("/testFolder1"));
  mFsShell.rename(new String[]{"rename","/testFolder1","/testFolder"});
  toCompare.append(getCommandOutput(new String[]{"mv","/testFolder1","/testFolder"}));
  Assert.assertEquals(toCompare.toString(),mOutput.toString());
  Assert.assertNotNull(mTfs.getFile("/testFolder"));
  Assert.assertNull(mTfs.getFile("/testFolder1"));
}
