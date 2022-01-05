/** 
 * Test <code>void read(byte b[])</code>. Read from underfs.
 */
@Test public void readTest7() throws IOException {
  for (int k=MIN_LEN + DELTA; k <= MAX_LEN; k+=DELTA) {
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
    Assert.assertEquals(-1,is.read(ret));
    is.close();
    Assert.assertFalse(file.isInMemory());
  }
}
