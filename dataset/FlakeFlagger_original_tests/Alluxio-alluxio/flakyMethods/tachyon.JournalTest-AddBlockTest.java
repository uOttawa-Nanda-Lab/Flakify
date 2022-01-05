/** 
 * Test add block
 * @throws Exception
 */
@Test public void AddBlockTest() throws Exception {
  mTfs.createFile("/xyz",64);
  TachyonFile file=mTfs.getFile("/xyz");
  OutputStream os=file.getOutStream(WriteType.MUST_CACHE);
  for (int k=0; k < 1000; k++) {
    os.write(k);
  }
  os.close();
  ClientFileInfo fInfo=mLocalTachyonCluster.getMasterInfo().getClientFileInfo("/xyz");
  mLocalTachyonCluster.stop();
  AddBlockTestUtil(fInfo);
  String editLogPath=mLocalTachyonCluster.getEditLogPath();
  UnderFileSystem.get(editLogPath).delete(editLogPath,true);
  AddBlockTestUtil(fInfo);
}
