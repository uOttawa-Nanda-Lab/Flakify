@Test public void batchRemoveChildTest(){
  InodeFolder inodeFolder=new InodeFolder("testFolder1",1,0,System.currentTimeMillis());
  InodeFile inodeFile1=new InodeFile("testFile1",2,1,1000,System.currentTimeMillis());
  InodeFile inodeFile2=new InodeFile("testFile2",3,1,1000,System.currentTimeMillis());
  inodeFolder.addChild(2);
  inodeFolder.addChild(3);
  inodeFolder.addChild(4);
  Map<Integer,Inode> testMap=new HashMap<Integer,Inode>(2);
  testMap.put(2,inodeFile1);
  testMap.put(3,inodeFile2);
  Assert.assertEquals(3,inodeFolder.getNumberOfChildren());
  inodeFolder.removeChild("testFile1",testMap);
  Assert.assertEquals(2,inodeFolder.getNumberOfChildren());
  Assert.assertFalse(inodeFolder.getChildrenIds().contains(2));
}
