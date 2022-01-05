/** 
 * Test <code>void read(byte b[])</code>. Read from underfs.
 */
@Test public void readTest2() throws IOException {
  for (int k=MIN_LEN; k <= MAX_LEN; k+=DELTA) {
    WriteType op=WriteType.THROUGH;
    int fileId=TestUtils.createByteFile(mTfs,"/root/testFile_" + k + "_"+ op,op,k);
    TachyonFile file=mTfs.getFile(fileId);
    InStream is=file.getInStream(ReadType.NO_CACHE);
    if (k == 0) {
      Assert.assertTrue(is instanceof EmptyBlockInStream);
    }
 else {
      Assert.assertTrue(is instanceof RemoteBlockInStream);
    }
    byte[] ret=new byte[k];
    Assert.assertEquals(k,is.read(ret));
    Assert.assertTrue(TestUtils.equalIncreasingByteArray(k,ret));
    is.close();
    if (k == 0) {
      Assert.assertTrue(file.isInMemory());
    }
 else {
      Assert.assertFalse(file.isInMemory());
    }
    is=file.getInStream(ReadType.CACHE);
    if (k == 0) {
      Assert.assertTrue(is instanceof EmptyBlockInStream);
    }
 else {
      Assert.assertTrue(is instanceof RemoteBlockInStream);
    }
    ret=new byte[k];
    Assert.assertEquals(k,is.read(ret));
    Assert.assertTrue(TestUtils.equalIncreasingByteArray(k,ret));
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
    Assert.assertEquals(k,is.read(ret));
    Assert.assertTrue(TestUtils.equalIncreasingByteArray(k,ret));
    is.close();
    Assert.assertTrue(file.isInMemory());
  }
}
