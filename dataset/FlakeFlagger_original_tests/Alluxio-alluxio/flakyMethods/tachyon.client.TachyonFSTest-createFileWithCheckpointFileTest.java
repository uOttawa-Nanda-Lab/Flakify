@Test public void createFileWithCheckpointFileTest() throws IOException {
  String tempFolder=mLocalTachyonCluster.getTempFolderInUnderFs();
  UnderFileSystem underFs=UnderFileSystem.get(tempFolder);
  OutputStream os=underFs.create(tempFolder + "/temp",100);
  os.close();
  mTfs.createFile("/abc",tempFolder + "/temp");
  Assert.assertTrue(mTfs.exist("/abc"));
  Assert.assertEquals(tempFolder + "/temp",mTfs.getCheckpointPath(mTfs.getFileId("/abc")));
}
