@Test public void lockBlockTest3() throws IOException {
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
    if (k < numOfFiles - 1) {
      Assert.assertNotNull(tFile.readByteBuffer());
    }
  }
  fileIds.add(TestUtils.createByteFile(mTfs,"/file_" + numOfFiles,WriteType.CACHE_THROUGH,fileSize));
  for (int k=0; k <= numOfFiles; k++) {
    tFile=mTfs.getFile(fileIds.get(k));
    if (k != numOfFiles - 1) {
      Assert.assertTrue(tFile.isInMemory());
    }
 else {
      CommonUtils.sleepMs(null,SLEEP_MS);
      Assert.assertFalse(tFile.isInMemory());
    }
  }
}
