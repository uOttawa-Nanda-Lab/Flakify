@Test public void mkdirComplexPathTest() throws IOException {
  mFsShell.mkdir(new String[]{"mkdir","/Complex!@#$%^&*()-_=+[]{};\"'<>,.?/File"});
  TachyonFile tFile=mTfs.getFile("/Complex!@#$%^&*()-_=+[]{};\"'<>,.?/File");
  Assert.assertNotNull(tFile);
  Assert.assertEquals(getCommandOutput(new String[]{"mkdir","/Complex!@#$%^&*()-_=+[]{};\"'<>,.?/File"}),mOutput.toString());
  Assert.assertTrue(tFile.isDirectory());
}
