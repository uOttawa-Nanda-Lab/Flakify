@Test public void deleteFileTest() throws IOException {
  List<ClientWorkerInfo> workers=mTfs.getWorkersInfo();
  Assert.assertEquals(1,workers.size());
  Assert.assertEquals(WORKER_CAPACITY_BYTES,workers.get(0).getCapacityBytes());
  Assert.assertEquals(0,workers.get(0).getUsedBytes());
  int writeBytes=USER_QUOTA_UNIT_BYTES * 2;
  for (int k=0; k < 5; k++) {
    int fileId=TestUtils.createByteFile(mTfs,"/file" + k,WriteType.MUST_CACHE,writeBytes);
    TachyonFile file=mTfs.getFile(fileId);
    Assert.assertTrue(file.isInMemory());
    Assert.assertTrue(mTfs.exist("/file" + k));
    workers=mTfs.getWorkersInfo();
    Assert.assertEquals(1,workers.size());
    Assert.assertEquals(WORKER_CAPACITY_BYTES,workers.get(0).getCapacityBytes());
    Assert.assertEquals(writeBytes * (k + 1),workers.get(0).getUsedBytes());
  }
  for (int k=0; k < 5; k++) {
    int fileId=mTfs.getFileId("/file" + k);
    mTfs.delete(fileId,true);
    Assert.assertFalse(mTfs.exist("/file" + k));
    CommonUtils.sleepMs(null,SLEEP_MS);
    workers=mTfs.getWorkersInfo();
    Assert.assertEquals(1,workers.size());
    Assert.assertEquals(WORKER_CAPACITY_BYTES,workers.get(0).getCapacityBytes());
    Assert.assertEquals(writeBytes * (4 - k),workers.get(0).getUsedBytes());
  }
}
