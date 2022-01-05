@Test(expected=SuspectedFileSizeException.class) public void inodeRepeatedLengthSetTest() throws SuspectedFileSizeException, BlockInfoException {
  InodeFile inodeFile=new InodeFile("testFile1",1,0,1000,System.currentTimeMillis());
  inodeFile.setLength(100);
  inodeFile.setLength(200);
}
