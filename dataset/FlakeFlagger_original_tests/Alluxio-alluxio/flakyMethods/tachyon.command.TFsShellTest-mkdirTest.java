@Test public void mkdirTest() throws IOException {
  mFsShell.mkdir(new String[]{"mkdir","tachyon://" + InetAddress.getLocalHost().getCanonicalHostName() + ":"+ mLocalTachyonCluster.getMasterPort()+ "/root/testFile1"});
  TachyonFile tFile=mTfs.getFile("/root/testFile1");
  Assert.assertNotNull(tFile);
  Assert.assertEquals(getCommandOutput(new String[]{"mkdir","/root/testFile1"}),mOutput.toString());
  Assert.assertTrue(tFile.isDirectory());
}
