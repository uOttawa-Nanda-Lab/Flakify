/** 
 * Test file and folder creation.
 * @throws Exception
 */
@Test public void FileFolderTest() throws Exception {
  for (int i=0; i < 10; i++) {
    mTfs.mkdir("/i" + i);
    for (int j=0; j < 10; j++) {
      mTfs.createFile("/i" + i + "/j"+ j,(i + j + 1) * 64);
    }
  }
  mLocalTachyonCluster.stop();
  FileFolderUtil();
  String editLogPath=mLocalTachyonCluster.getEditLogPath();
  UnderFileSystem.get(editLogPath).delete(editLogPath,true);
  FileFolderUtil();
}
