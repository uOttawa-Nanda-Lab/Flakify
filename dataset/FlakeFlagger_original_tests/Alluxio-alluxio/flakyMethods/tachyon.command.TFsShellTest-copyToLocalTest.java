@Test public void copyToLocalTest() throws IOException {
  TestUtils.createByteFile(mTfs,"/testFile",WriteType.MUST_CACHE,10);
  mFsShell.copyToLocal(new String[]{"copyToLocal","/testFile",mLocalTachyonCluster.getTachyonHome() + "/testFile"});
  Assert.assertEquals(getCommandOutput(new String[]{"copyToLocal","/testFile",mLocalTachyonCluster.getTachyonHome() + "/testFile"}),mOutput.toString());
  File testFile=new File(mLocalTachyonCluster.getTachyonHome() + "/testFile");
  FileInputStream fis=new FileInputStream(testFile);
  byte read[]=new byte[10];
  fis.read(read);
  fis.close();
  Assert.assertTrue(TestUtils.equalIncreasingByteArray(10,read));
}
