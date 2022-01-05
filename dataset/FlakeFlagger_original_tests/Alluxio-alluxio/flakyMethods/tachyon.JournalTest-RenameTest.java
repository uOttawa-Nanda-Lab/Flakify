/** 
 * Test file and folder creation, and rename;
 * @throws Exception
 */
@Test public void RenameTest() throws Exception {
  for (int i=0; i < 10; i++) {
    mTfs.mkdir("/i" + i);
    for (int j=0; j < 10; j++) {
      mTfs.createFile("/i" + i + "/j"+ j,(i + j + 1) * 64);
      mTfs.rename("/i" + i + "/j"+ j,"/i" + i + "/jj"+ j);
    }
    mTfs.rename("/i" + i,"/ii" + i);
  }
  mLocalTachyonCluster.stop();
  RenameTestUtil();
  String editLogPath=mLocalTachyonCluster.getEditLogPath();
  UnderFileSystem.get(editLogPath).delete(editLogPath,true);
  RenameTestUtil();
}
