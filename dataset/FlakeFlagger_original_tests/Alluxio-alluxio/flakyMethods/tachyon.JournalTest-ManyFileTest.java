/** 
 * Test files creation.
 * @throws Exception
 */
@Test public void ManyFileTest() throws Exception {
  for (int i=0; i < 10; i++) {
    mTfs.createFile("/a" + i,(i + 1) * 64);
  }
  mLocalTachyonCluster.stop();
  ManyFileTestUtil();
  String editLogPath=mLocalTachyonCluster.getEditLogPath();
  UnderFileSystem.get(editLogPath).delete(editLogPath,true);
  ManyFileTestUtil();
}
