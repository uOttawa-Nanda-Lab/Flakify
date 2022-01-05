@Test public void generateClientBlockInfoTest(){
  BlockInfo tInfo=new BlockInfo(new InodeFile("t",100,0,Constants.DEFAULT_BLOCK_SIZE_BYTE,System.currentTimeMillis()),300,800);
  tInfo.addLocation(15,new NetAddress("abc",1));
  tInfo.addLocation(22,new NetAddress("def",2));
  tInfo.addLocation(29,new NetAddress("gh",3));
  ClientBlockInfo clientBlockInfo=tInfo.generateClientBlockInfo();
  Assert.assertEquals((long)Constants.DEFAULT_BLOCK_SIZE_BYTE * 300,clientBlockInfo.offset);
  Assert.assertEquals(800,clientBlockInfo.length);
  Assert.assertEquals(3,clientBlockInfo.locations.size());
}
