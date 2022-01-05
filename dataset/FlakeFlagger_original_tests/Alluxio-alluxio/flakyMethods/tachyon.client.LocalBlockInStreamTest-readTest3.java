/** 
 * Test <code>void read(byte[] b, int off, int len)</code>.
 */
@Test public void readTest3() throws IOException {
  for (int k=MIN_LEN; k <= MAX_LEN; k+=DELTA) {
    for (    WriteType op : mWriteCacheType) {
      int fileId=TestUtils.createByteFile(mTfs,"/root/testFile_" + k + "_"+ op,op,k);
      TachyonFile file=mTfs.getFile(fileId);
      InStream is=file.getInStream(ReadType.NO_CACHE);
      if (k == 0) {
        Assert.assertTrue(is instanceof EmptyBlockInStream);
      }
 else {
        Assert.assertTrue(is instanceof LocalBlockInStream);
      }
      byte[] ret=new byte[k / 2];
      Assert.assertEquals(k / 2,is.read(ret,0,k / 2));
      Assert.assertTrue(TestUtils.equalIncreasingByteArray(k / 2,ret));
      is.close();
      Assert.assertTrue(file.isInMemory());
      is=file.getInStream(ReadType.CACHE);
      if (k == 0) {
        Assert.assertTrue(is instanceof EmptyBlockInStream);
      }
 else {
        Assert.assertTrue(is instanceof LocalBlockInStream);
      }
      ret=new byte[k];
      Assert.assertEquals(k,is.read(ret,0,k));
      Assert.assertTrue(TestUtils.equalIncreasingByteArray(k,ret));
      is.close();
      Assert.assertTrue(file.isInMemory());
    }
  }
}
