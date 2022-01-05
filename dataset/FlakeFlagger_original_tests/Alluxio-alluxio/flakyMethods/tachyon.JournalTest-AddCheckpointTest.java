/** 
 * Test add checkpoint
 * @throws Exception
 */
@Test public void AddCheckpointTest() throws Exception {
  TestUtils.createByteFile(mTfs,"/xyz",WriteType.THROUGH,10);
  ClientFileInfo fInfo=mLocalTachyonCluster.getMasterInfo().getClientFileInfo("/xyz");
  String ckPath=fInfo.getCheckpointPath();
  mTfs.createFile("/xyz_ck",ckPath);
  ClientFileInfo ckFileInfo=mLocalTachyonCluster.getMasterInfo().getClientFileInfo("/xyz_ck");
  mLocalTachyonCluster.stop();
  AddCheckpointTestUtil(fInfo,ckFileInfo);
  String editLogPath=mLocalTachyonCluster.getEditLogPath();
  UnderFileSystem.get(editLogPath).delete(editLogPath,true);
  AddCheckpointTestUtil(fInfo,ckFileInfo);
}
