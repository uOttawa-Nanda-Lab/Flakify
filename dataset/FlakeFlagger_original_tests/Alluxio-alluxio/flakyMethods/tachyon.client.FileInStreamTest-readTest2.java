/** 
 * Test <code>void read(byte b[])</code>.
 */
@Test public void readTest2() throws IOException {
  for (int k=MIN_LEN; k <= MAX_LEN; k+=DELTA) {
    for (    WriteType op : WriteType.values()) {
      int fileId=TestUtils.createByteFile(mTfs,"/root/testFile_" + k + "_"+ op,op,k);
      TachyonFile file=mTfs.getFile(fileId);
      InStream is=(k < MEAN ? file.getInStream(ReadType.CACHE) : file.getInStream(ReadType.NO_CACHE));
      Assert.assertTrue(is instanceof FileInStream);
      byte[] ret=new byte[k];
      Assert.assertEquals(k,is.read(ret));
      Assert.assertTrue(TestUtils.equalIncreasingByteArray(k,ret));
      is.close();
      is=(k < MEAN ? file.getInStream(ReadType.CACHE) : file.getInStream(ReadType.NO_CACHE));
      Assert.assertTrue(is instanceof FileInStream);
      ret=new byte[k];
      Assert.assertEquals(k,is.read(ret));
      Assert.assertTrue(TestUtils.equalIncreasingByteArray(k,ret));
      is.close();
    }
  }
}
