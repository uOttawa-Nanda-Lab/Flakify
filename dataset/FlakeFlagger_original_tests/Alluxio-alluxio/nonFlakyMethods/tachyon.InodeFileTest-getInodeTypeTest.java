@Test public void getInodeTypeTest(){
  InodeFile inode1=new InodeFile("test1",1,0,1000,System.currentTimeMillis());
  Assert.assertEquals(InodeType.File,inode1.getInodeType());
}
