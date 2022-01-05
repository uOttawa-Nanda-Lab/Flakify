@Test public void inodeLengthTest() throws SuspectedFileSizeException, BlockInfoException {
  InodeFile inodeFile=new InodeFile("testFile1",1,0,1000,System.currentTimeMillis());
  long testLength=100;
  inodeFile.setLength(testLength);
  Assert.assertEquals(testLength,inodeFile.getLength());
}
