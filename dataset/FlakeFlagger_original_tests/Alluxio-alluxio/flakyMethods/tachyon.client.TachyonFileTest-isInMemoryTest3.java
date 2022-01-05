/** 
 * Test LRU Cache Eviction + PIN.
 * @throws InvalidPathException
 * @throws FileAlreadyExistException
 * @throws IOException
 */
@Test public void isInMemoryTest3() throws InvalidPathException, FileAlreadyExistException, IOException {
  int fileId=TestUtils.createByteFile(mTfs,"/pin/file",WriteType.MUST_CACHE,USER_QUOTA_UNIT_BYTES);
  TachyonFile file=mTfs.getFile(fileId);
  Assert.assertTrue(file.isInMemory());
  for (int k=0; k < MAX_FILES; k++) {
    fileId=TestUtils.createByteFile(mTfs,"/file" + k,WriteType.MUST_CACHE,USER_QUOTA_UNIT_BYTES);
    file=mTfs.getFile(fileId);
    Assert.assertTrue(file.isInMemory());
  }
  CommonUtils.sleepMs(null,WORKER_TO_MASTER_HEARTBEAT_INTERVAL_MS);
  file=mTfs.getFile("/pin/file");
  Assert.assertTrue(file.isInMemory());
  file=mTfs.getFile("/file0");
  Assert.assertFalse(file.isInMemory());
  for (int k=1; k < MAX_FILES; k++) {
    file=mTfs.getFile("/file" + k);
    Assert.assertTrue(file.isInMemory());
  }
}
