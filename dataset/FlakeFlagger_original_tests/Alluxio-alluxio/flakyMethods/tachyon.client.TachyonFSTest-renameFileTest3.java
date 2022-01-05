@Test public void renameFileTest3() throws IOException {
  int fileId=mTfs.createFile("/root/testFile0");
  TachyonFile file=mTfs.getFile("/root/testFile0");
  for (int k=1; k < 10; k++) {
    Assert.assertTrue(mTfs.exist("/root/testFile" + (k - 1)));
    Assert.assertTrue(file.rename("/root/testFile" + k));
    Assert.assertEquals(fileId,mTfs.getFileId("/root/testFile" + k));
    Assert.assertFalse(mTfs.exist("/root/testFile" + (k - 1)));
  }
}
