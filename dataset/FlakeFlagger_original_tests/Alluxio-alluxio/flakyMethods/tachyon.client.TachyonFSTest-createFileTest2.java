@Test public void createFileTest2() throws Exception {
  Assert.assertEquals(3,mTfs.createFile("/root/testFile1"));
  Assert.assertTrue(mTfs.exist("/root/testFile1"));
  Assert.assertEquals(4,mTfs.createFile("/root/testFile2"));
  Assert.assertTrue(mTfs.exist("/root/testFile2"));
  Assert.assertEquals(5,mTfs.createFile("/root/testFile3"));
  Assert.assertTrue(mTfs.exist("/root/testFile3"));
}
