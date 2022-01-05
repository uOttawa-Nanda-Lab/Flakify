@Test public void rmTest() throws IOException {
  StringBuilder toCompare=new StringBuilder();
  mFsShell.mkdir(new String[]{"mkdir","/testFolder1/testFolder2/testFile2"});
  toCompare.append(getCommandOutput(new String[]{"mkdir","/testFolder1/testFolder2/testFile2"}));
  Assert.assertNotNull(mTfs.getFile("/testFolder1"));
  Assert.assertNotNull(mTfs.getFile("/testFolder1/testFolder2"));
  Assert.assertNotNull(mTfs.getFile("/testFolder1/testFolder2/testFile2"));
  mFsShell.rm(new String[]{"rm","/testFolder1/testFolder2/testFile2"});
  toCompare.append(getCommandOutput(new String[]{"rm","/testFolder1/testFolder2/testFile2"}));
  Assert.assertEquals(toCompare.toString(),mOutput.toString());
  Assert.assertNotNull(mTfs.getFile("/testFolder1"));
  Assert.assertNotNull(mTfs.getFile("/testFolder1/testFolder2"));
  Assert.assertNull(mTfs.getFile("/testFolder1/testFolder2/testFile2"));
  mFsShell.rm(new String[]{"rm","/testFolder1"});
  toCompare.append(getCommandOutput(new String[]{"rm","/testFolder1"}));
  Assert.assertEquals(toCompare.toString(),mOutput.toString());
  Assert.assertNull(mTfs.getFile("/testFolder1"));
  Assert.assertNull(mTfs.getFile("/testFolder1/testFolder2"));
  Assert.assertNull(mTfs.getFile("/testFolder1/testFolder2/testFile2"));
}
