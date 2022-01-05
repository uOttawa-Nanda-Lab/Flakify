@Test(expected=BlockInfoException.class) public void inMemoryLocationsTestWithBlockInfoException() throws IOException, BlockInfoException {
  InodeFile inodeFile=new InodeFile("testFile1",1,0,1000,System.currentTimeMillis());
  inodeFile.addLocation(0,1,new NetAddress("testhost1",1000));
}
