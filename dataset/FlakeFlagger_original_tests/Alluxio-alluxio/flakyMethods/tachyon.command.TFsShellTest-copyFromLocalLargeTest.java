@Test public void copyFromLocalLargeTest() throws IOException {
  File testFile=new File(mLocalTachyonCluster.getTachyonHome() + "/testFile");
  testFile.createNewFile();
  FileOutputStream fos=new FileOutputStream(testFile);
  byte toWrite[]=TestUtils.getIncreasingByteArray(mSizeBytes);
  fos.write(toWrite);
  fos.close();
  mFsShell.copyFromLocal(new String[]{"copyFromLocal",testFile.getAbsolutePath(),"/testFile"});
  Assert.assertEquals(getCommandOutput(new String[]{"copyFromLocal",testFile.getAbsolutePath(),"/testFile"}),mOutput.toString());
  TachyonFile tFile=mTfs.getFile("/testFile");
  Assert.assertNotNull(tFile);
  Assert.assertEquals(mSizeBytes,tFile.length());
  InStream tfis=tFile.getInStream(ReadType.NO_CACHE);
  byte read[]=new byte[mSizeBytes];
  tfis.read(read);
  Assert.assertTrue(TestUtils.equalIncreasingByteArray(mSizeBytes,read));
}
