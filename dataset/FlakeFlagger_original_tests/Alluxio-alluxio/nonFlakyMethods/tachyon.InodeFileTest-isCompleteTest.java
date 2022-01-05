@Test public void isCompleteTest() throws SuspectedFileSizeException, BlockInfoException {
  InodeFile inodeFile=new InodeFile("testFile1",1,0,1000,System.currentTimeMillis());
  Assert.assertFalse(inodeFile.isComplete());
  inodeFile.setComplete();
  Assert.assertTrue(inodeFile.isComplete());
}
