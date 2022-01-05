@Test public void inMemoryLocationsTest() throws IOException, BlockInfoException {
  InodeFile inodeFile=new InodeFile("testFile1",1,0,1000,System.currentTimeMillis());
  List<NetAddress> testAddresses=new ArrayList<NetAddress>(3);
  testAddresses.add(new NetAddress("testhost1",1000));
  testAddresses.add(new NetAddress("testhost2",2000));
  testAddresses.add(new NetAddress("testhost3",3000));
  inodeFile.addBlock(new BlockInfo(inodeFile,0,5));
  inodeFile.addLocation(0,1,testAddresses.get(0));
  Assert.assertEquals(1,inodeFile.getBlockLocations(0).size());
  inodeFile.addLocation(0,2,testAddresses.get(1));
  Assert.assertEquals(2,inodeFile.getBlockLocations(0).size());
  inodeFile.addLocation(0,3,testAddresses.get(2));
  Assert.assertEquals(3,inodeFile.getBlockLocations(0).size());
  Assert.assertEquals(testAddresses,inodeFile.getBlockLocations(0));
}
