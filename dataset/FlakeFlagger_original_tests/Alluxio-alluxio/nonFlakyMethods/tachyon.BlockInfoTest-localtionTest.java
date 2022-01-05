@Test public void localtionTest(){
  BlockInfo tInfo=new BlockInfo(new InodeFile("t",100,0,Constants.DEFAULT_BLOCK_SIZE_BYTE,System.currentTimeMillis()),300,800);
  tInfo.addLocation(15,new NetAddress("abc",1));
  Assert.assertEquals(1,tInfo.getLocations().size());
  tInfo.addLocation(22,new NetAddress("def",2));
  Assert.assertEquals(2,tInfo.getLocations().size());
  tInfo.addLocation(29,new NetAddress("gh",3));
  Assert.assertEquals(3,tInfo.getLocations().size());
  tInfo.addLocation(15,new NetAddress("abc",1));
  Assert.assertEquals(3,tInfo.getLocations().size());
  tInfo.addLocation(22,new NetAddress("def",2));
  Assert.assertEquals(3,tInfo.getLocations().size());
  tInfo.addLocation(29,new NetAddress("gh",3));
  Assert.assertEquals(3,tInfo.getLocations().size());
  tInfo.removeLocation(15);
  Assert.assertEquals(2,tInfo.getLocations().size());
  tInfo.removeLocation(10);
  Assert.assertEquals(2,tInfo.getLocations().size());
}
