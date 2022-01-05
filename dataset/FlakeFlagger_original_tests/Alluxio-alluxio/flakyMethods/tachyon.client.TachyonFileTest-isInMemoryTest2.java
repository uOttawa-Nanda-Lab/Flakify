/** 
 * Test LRU Cache Eviction.
 * @throws InvalidPathException
 * @throws FileAlreadyExistException
 * @throws IOException
 */
@Test public void isInMemoryTest2() throws InvalidPathException, FileAlreadyExistException, IOException {
  for (int k=0; k < MAX_FILES; k++) {
    int fileId=TestUtils.createByteFile(mTfs,"/file" + k,WriteType.MUST_CACHE,USER_QUOTA_UNIT_BYTES);
    TachyonFile file=mTfs.getFile(fileId);
    Assert.assertTrue(file.isInMemory());
  }
  CommonUtils.sleepMs(null,WORKER_TO_MASTER_HEARTBEAT_INTERVAL_MS);
  for (int k=0; k < MAX_FILES; k++) {
    TachyonFile file=mTfs.getFile("/file" + k);
    Assert.assertTrue(file.isInMemory());
  }
  for (int k=MAX_FILES; k < MAX_FILES + 1; k++) {
    int fileId=TestUtils.createByteFile(mTfs,"/file" + k,WriteType.MUST_CACHE,USER_QUOTA_UNIT_BYTES);
    TachyonFile file=mTfs.getFile(fileId);
    Assert.assertTrue(file.isInMemory());
  }
  CommonUtils.sleepMs(null,WORKER_TO_MASTER_HEARTBEAT_INTERVAL_MS);
  TachyonFile file=mTfs.getFile("/file" + 0);
  Assert.assertFalse(file.isInMemory());
  for (int k=1; k < MAX_FILES + 1; k++) {
    file=mTfs.getFile("/file" + k);
    Assert.assertTrue(file.isInMemory());
  }
}
