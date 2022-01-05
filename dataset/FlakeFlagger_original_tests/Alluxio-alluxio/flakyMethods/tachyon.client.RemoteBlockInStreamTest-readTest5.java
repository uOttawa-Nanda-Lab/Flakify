/** 
 * Test <code>void read(byte b[])</code>. Read from remote data server.
 */
@Test public void readTest5() throws IOException {
  for (int k=MIN_LEN + DELTA; k <= MAX_LEN; k+=DELTA) {
    WriteType op=WriteType.MUST_CACHE;
    int fileId=TestUtils.createByteFile(mTfs,"/root/testFile_" + k + "_"+ op,op,k);
    TachyonFile file=mTfs.getFile(fileId);
    InStream is=new RemoteBlockInStream(file,ReadType.NO_CACHE,0);
    Assert.assertTrue(is instanceof RemoteBlockInStream);
    byte[] ret=new byte[k];
    int start=0;
    while (start < k) {
      int read=is.read(ret);
      Assert.assertTrue(TestUtils.equalIncreasingByteArray(start,read,ret));
      start+=read;
    }
    is.close();
    Assert.assertTrue(file.isInMemory());
  }
}
