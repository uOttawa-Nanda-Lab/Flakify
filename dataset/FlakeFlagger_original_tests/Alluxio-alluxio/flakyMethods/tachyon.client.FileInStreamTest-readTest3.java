/** 
 * Test <code>void read(byte[] b, int off, int len)</code>.
 */
@Test public void readTest3() throws IOException {
  for (int k=MIN_LEN; k <= MAX_LEN; k+=DELTA) {
    for (    WriteType op : WriteType.values()) {
      int fileId=TestUtils.createByteFile(mTfs,"/root/testFile_" + k + "_"+ op,op,k);
      TachyonFile file=mTfs.getFile(fileId);
      InStream is=(k < MEAN ? file.getInStream(ReadType.CACHE) : file.getInStream(ReadType.NO_CACHE));
      Assert.assertTrue(is instanceof FileInStream);
      byte[] ret=new byte[k / 2];
      Assert.assertEquals(k / 2,is.read(ret,0,k / 2));
      Assert.assertTrue(TestUtils.equalIncreasingByteArray(k / 2,ret));
      is.close();
      is=(k < MEAN ? file.getInStream(ReadType.CACHE) : file.getInStream(ReadType.NO_CACHE));
      Assert.assertTrue(is instanceof FileInStream);
      ret=new byte[k];
      Assert.assertEquals(k,is.read(ret,0,k));
      Assert.assertTrue(TestUtils.equalIncreasingByteArray(k,ret));
      is.close();
    }
  }
}
