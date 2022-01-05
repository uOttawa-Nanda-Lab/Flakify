@Test public void getInodeTypeTest(){
  InodeFolder inode1=new InodeFolder("test1",1,0,System.currentTimeMillis());
  Assert.assertEquals(InodeType.Folder,inode1.getInodeType());
}
