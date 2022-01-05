@Test public void inMemoryTest() throws BlockInfoException {
  InodeFile inodeFile=new InodeFile("testFile1",1,0,1000,System.currentTimeMillis());
  inodeFile.addBlock(new BlockInfo(inodeFile,0,5));
  Assert.assertFalse(inodeFile.isFullyInMemory());
  inodeFile.addLocation(0,1,new NetAddress("testhost1",1000));
  Assert.assertTrue(inodeFile.isFullyInMemory());
  inodeFile.removeLocation(0,1);
  Assert.assertFalse(inodeFile.isFullyInMemory());
  inodeFile.addLocation(0,1,new NetAddress("testhost1",1000));
  inodeFile.addLocation(0,1,new NetAddress("testhost1",1000));
  Assert.assertTrue(inodeFile.isFullyInMemory());
  inodeFile.removeLocation(0,1);
  Assert.assertFalse(inodeFile.isFullyInMemory());
  inodeFile.addLocation(0,1,new NetAddress("testhost1",1000));
  inodeFile.addLocation(0,2,new NetAddress("testhost1",1000));
  Assert.assertTrue(inodeFile.isFullyInMemory());
  inodeFile.removeLocation(0,1);
  Assert.assertTrue(inodeFile.isFullyInMemory());
}
