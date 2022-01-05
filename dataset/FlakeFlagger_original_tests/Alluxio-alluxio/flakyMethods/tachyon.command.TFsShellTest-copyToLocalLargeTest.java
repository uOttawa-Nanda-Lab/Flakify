@Test public void copyToLocalLargeTest() throws IOException {
  TestUtils.createByteFile(mTfs,"/testFile",WriteType.MUST_CACHE,mSizeBytes);
  mFsShell.copyToLocal(new String[]{"copyToLocal","/testFile",mLocalTachyonCluster.getTachyonHome() + "/testFile"});
  Assert.assertEquals(getCommandOutput(new String[]{"copyToLocal","/testFile",mLocalTachyonCluster.getTachyonHome() + "/testFile"}),mOutput.toString());
  File testFile=new File(mLocalTachyonCluster.getTachyonHome() + "/testFile");
  FileInputStream fis=new FileInputStream(testFile);
  byte read[]=new byte[mSizeBytes];
  fis.read(read);
  fis.close();
  Assert.assertTrue(TestUtils.equalIncreasingByteArray(mSizeBytes,read));
}
