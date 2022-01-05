/** 
 * Test <code>void read()</code>. Read from remote data server.
 */
@Test public void readTest4() throws IOException {
  for (int k=MIN_LEN + DELTA; k <= MAX_LEN; k+=DELTA) {
    WriteType op=WriteType.MUST_CACHE;
    int fileId=TestUtils.createByteFile(mTfs,"/root/testFile_" + k + "_"+ op,op,k);
    TachyonFile file=mTfs.getFile(fileId);
    RemoteBlockInStream is=new RemoteBlockInStream(file,ReadType.NO_CACHE,0);
    Assert.assertTrue(is instanceof RemoteBlockInStream);
    byte[] ret=new byte[k];
    int value=is.read();
    int cnt=0;
    while (value != -1) {
      ret[cnt++]=(byte)value;
      value=is.read();
    }
    Assert.assertTrue(TestUtils.equalIncreasingByteArray(k,ret));
    is.close();
    Assert.assertTrue(file.isInMemory());
  }
}
