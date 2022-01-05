@Test public void unlockBlockTest1() throws IOException {
  TachyonFile tFile=null;
  int numOfFiles=5;
  int fileSize=WORKER_CAPACITY_BYTES / numOfFiles;
  List<Integer> fileIds=new ArrayList<Integer>();
  for (int k=0; k < numOfFiles; k++) {
    fileIds.add(TestUtils.createByteFile(mTfs,"/file_" + k,WriteType.CACHE_THROUGH,fileSize));
  }
  for (int k=0; k < numOfFiles; k++) {
    tFile=mTfs.getFile(fileIds.get(k));
    Assert.assertTrue(tFile.isInMemory());
    TachyonByteBuffer tBuf=tFile.readByteBuffer();
    Assert.assertNotNull(tBuf);
    tBuf.close();
  }
  fileIds.add(TestUtils.createByteFile(mTfs,"/file_" + numOfFiles,WriteType.CACHE_THROUGH,fileSize));
  CommonUtils.sleepMs(null,SLEEP_MS);
  tFile=mTfs.getFile(fileIds.get(0));
  Assert.assertFalse(tFile.isInMemory());
  for (int k=1; k <= numOfFiles; k++) {
    tFile=mTfs.getFile(fileIds.get(k));
    Assert.assertTrue(tFile.isInMemory());
  }
}
