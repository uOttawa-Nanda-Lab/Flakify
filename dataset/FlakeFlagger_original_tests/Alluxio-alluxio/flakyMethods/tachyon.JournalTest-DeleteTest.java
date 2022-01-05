/** 
 * Test file and folder creation and deletion;
 * @throws Exception
 */
@Test public void DeleteTest() throws Exception {
  for (int i=0; i < 10; i++) {
    mTfs.mkdir("/i" + i);
    for (int j=0; j < 10; j++) {
      mTfs.createFile("/i" + i + "/j"+ j,(i + j + 1) * 64);
      if (j >= 5) {
        mTfs.delete("/i" + i + "/j"+ j,false);
      }
    }
    if (i >= 5) {
      mTfs.delete("/i" + i,true);
    }
  }
  mLocalTachyonCluster.stop();
  DeleteTestUtil();
  String editLogPath=mLocalTachyonCluster.getEditLogPath();
  UnderFileSystem.get(editLogPath).delete(editLogPath,true);
  DeleteTestUtil();
}
