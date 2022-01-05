@Test public void renameFileTest1() throws IOException {
  int fileId=mTfs.createFile("/root/testFile1");
  for (int k=1; k < 10; k++) {
    Assert.assertTrue(mTfs.exist("/root/testFile" + k));
    Assert.assertTrue(mTfs.rename("/root/testFile" + k,"/root/testFile" + (k + 1)));
    Assert.assertEquals(fileId,mTfs.getFileId("/root/testFile" + (k + 1)));
    Assert.assertFalse(mTfs.exist("/root/testFile" + k));
  }
}
