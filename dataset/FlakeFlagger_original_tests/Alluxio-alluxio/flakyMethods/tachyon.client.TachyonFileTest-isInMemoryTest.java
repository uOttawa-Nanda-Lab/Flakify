/** 
 * Basic isInMemory Test.
 * @throws InvalidPathException
 * @throws FileAlreadyExistException
 * @throws IOException
 */
@Test public void isInMemoryTest() throws InvalidPathException, FileAlreadyExistException, IOException {
  int fileId=TestUtils.createByteFile(mTfs,"/file1",WriteType.MUST_CACHE,USER_QUOTA_UNIT_BYTES);
  TachyonFile file=mTfs.getFile(fileId);
  Assert.assertTrue(file.isInMemory());
  fileId=TestUtils.createByteFile(mTfs,"/file2",WriteType.CACHE_THROUGH,USER_QUOTA_UNIT_BYTES);
  file=mTfs.getFile(fileId);
  Assert.assertTrue(file.isInMemory());
  fileId=TestUtils.createByteFile(mTfs,"/file3",WriteType.THROUGH,USER_QUOTA_UNIT_BYTES);
  file=mTfs.getFile(fileId);
  Assert.assertFalse(file.isInMemory());
  Assert.assertTrue(file.recache());
  Assert.assertTrue(file.isInMemory());
  fileId=TestUtils.createByteFile(mTfs,"/file4",WriteType.THROUGH,WORKER_CAPACITY_BYTES + 1);
  file=mTfs.getFile(fileId);
  Assert.assertFalse(file.isInMemory());
  Assert.assertFalse(file.recache());
  Assert.assertFalse(file.isInMemory());
  fileId=TestUtils.createByteFile(mTfs,"/file5",WriteType.THROUGH,WORKER_CAPACITY_BYTES);
  file=mTfs.getFile(fileId);
  Assert.assertFalse(file.isInMemory());
  Assert.assertTrue(file.recache());
  Assert.assertTrue(file.isInMemory());
}
