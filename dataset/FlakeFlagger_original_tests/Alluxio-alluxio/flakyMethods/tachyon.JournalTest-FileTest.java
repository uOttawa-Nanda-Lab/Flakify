/** 
 * Test files creation.
 * @throws Exception
 */
@Test public void FileTest() throws Exception {
  mTfs.createFile("/xyz",64);
  ClientFileInfo fInfo=mLocalTachyonCluster.getMasterInfo().getClientFileInfo("/xyz");
  mLocalTachyonCluster.stop();
  FileTestUtil(fInfo);
  String editLogPath=mLocalTachyonCluster.getEditLogPath();
  UnderFileSystem.get(editLogPath).delete(editLogPath,true);
  FileTestUtil(fInfo);
}
