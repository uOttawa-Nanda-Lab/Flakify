@Test public void lockBlockTest4() throws IOException {
  TachyonFile tFile=null;
  int numOfFiles=5;
  int fileSize=WORKER_CAPACITY_BYTES / numOfFiles;
  List<Integer> fileIds=new ArrayList<Integer>();
  for (int k=0; k <= numOfFiles; k++) {
    fileIds.add(TestUtils.createByteFile(mTfs,"/file_" + k,WriteType.CACHE_THROUGH,fileSize));
  }
  for (int k=0; k <= numOfFiles; k++) {
    tFile=mTfs.getFile(fileIds.get(k));
    CommonUtils.sleepMs(null,SLEEP_MS);
    Assert.assertFalse(tFile.isInMemory());
    if (k < numOfFiles) {
      Assert.assertNull(tFile.readByteBuffer());
      Assert.assertTrue(tFile.recache());
      Assert.assertNotNull(tFile.readByteBuffer());
    }
 else {
      Assert.assertNull(tFile.readByteBuffer());
      Assert.assertFalse(tFile.recache());
      Assert.assertNull(tFile.readByteBuffer());
    }
  }
}
