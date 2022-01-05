@Test public void writeEmptyFileTest() throws IOException {
  Assert.assertEquals(2,mTfs.createFile("/emptyFile"));
  Assert.assertTrue(mTfs.exist("/emptyFile"));
  TachyonFile file=mTfs.getFile("/emptyFile");
  Assert.assertEquals(0,file.length());
  OutStream os=file.getOutStream(WriteType.CACHE_THROUGH);
  os.close();
  Assert.assertEquals(0,file.length());
}
