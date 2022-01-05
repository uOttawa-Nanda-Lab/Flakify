/** 
 * Test folder creation.
 * @throws Exception
 */
@Test public void FolderTest() throws Exception {
  mTfs.mkdir("/xyz");
  ClientFileInfo fInfo=mLocalTachyonCluster.getMasterInfo().getClientFileInfo("/xyz");
  mLocalTachyonCluster.stop();
  FolderTest(fInfo);
  String editLogPath=mLocalTachyonCluster.getEditLogPath();
  UnderFileSystem.get(editLogPath).delete(editLogPath,true);
  FolderTest(fInfo);
}
