/** 
 * Test <code>long skip(long len)</code>.
 */
@Test public void skipTest() throws IOException {
  for (int k=MIN_LEN + DELTA; k <= MAX_LEN; k+=DELTA) {
    for (    WriteType op : WriteType.values()) {
      int fileId=TestUtils.createByteFile(mTfs,"/root/testFile_" + k + "_"+ op,op,k);
      TachyonFile file=mTfs.getFile(fileId);
      InStream is=(k < MEAN ? file.getInStream(ReadType.CACHE) : file.getInStream(ReadType.NO_CACHE));
      Assert.assertTrue(is instanceof BlockInStream);
      Assert.assertEquals(k / 2,is.skip(k / 2));
      Assert.assertEquals(k / 2,is.read());
      is.close();
      is=(k < MEAN ? file.getInStream(ReadType.CACHE) : file.getInStream(ReadType.NO_CACHE));
      Assert.assertTrue(is instanceof BlockInStream);
      int t=k / 3;
      Assert.assertEquals(t,is.skip(t));
      Assert.assertEquals(t,is.read());
      Assert.assertEquals(t,is.skip(t));
      Assert.assertEquals(2 * t + 1,is.read());
      is.close();
    }
  }
}
