@Test public void evictionTest() throws InvalidPathException, FileAlreadyExistException, IOException, FileDoesNotExistException, TException {
  int fileId1=TestUtils.createByteFile(mTfs,"/file1",WriteType.MUST_CACHE,(int)WORKER_CAPACITY_BYTES / 3);
  Assert.assertTrue(fileId1 >= 0);
  ClientFileInfo fileInfo1=mMasterInfo.getClientFileInfo("/file1");
  Assert.assertTrue(fileInfo1.isInMemory());
  int fileId2=TestUtils.createByteFile(mTfs,"/file2",WriteType.MUST_CACHE,(int)WORKER_CAPACITY_BYTES / 3);
  Assert.assertTrue(fileId2 >= 0);
  fileInfo1=mMasterInfo.getClientFileInfo("/file1");
  ClientFileInfo fileInfo2=mMasterInfo.getClientFileInfo("/file2");
  Assert.assertTrue(fileInfo1.isInMemory());
  Assert.assertTrue(fileInfo2.isInMemory());
  int fileId3=TestUtils.createByteFile(mTfs,"/file3",WriteType.MUST_CACHE,(int)WORKER_CAPACITY_BYTES / 2);
  CommonUtils.sleepMs(null,WORKER_TO_MASTER_HEARTBEAT_INTERVAL_MS);
  fileInfo1=mMasterInfo.getClientFileInfo("/file1");
  fileInfo2=mMasterInfo.getClientFileInfo("/file2");
  ClientFileInfo fileInfo3=mMasterInfo.getClientFileInfo("/file3");
  Assert.assertTrue(fileId3 >= 0);
  Assert.assertFalse(fileInfo1.isInMemory());
  Assert.assertTrue(fileInfo2.isInMemory());
  Assert.assertTrue(fileInfo3.isInMemory());
}
