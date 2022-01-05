@Test public void clientFileInfoDirectoryTest() throws InvalidPathException, FileDoesNotExistException, FileAlreadyExistException, TachyonException {
  Assert.assertTrue(mMasterInfo.mkdir("/testFolder"));
  ClientFileInfo fileInfo=mMasterInfo.getClientFileInfo("/testFolder");
  Assert.assertEquals("testFolder",fileInfo.getName());
  Assert.assertEquals(2,fileInfo.getId());
  Assert.assertEquals(0,fileInfo.getLength());
  Assert.assertEquals("",fileInfo.getCheckpointPath());
  Assert.assertTrue(fileInfo.isFolder());
  Assert.assertFalse(fileInfo.isNeedPin());
  Assert.assertFalse(fileInfo.isNeedCache());
  Assert.assertTrue(fileInfo.isComplete());
}
