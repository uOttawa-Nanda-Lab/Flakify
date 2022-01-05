@Test public void setParentIdTest(){
  InodeFile inode1=new InodeFile("test1",1,0,1000,System.currentTimeMillis());
  Assert.assertEquals(0,inode1.getParentId());
  inode1.setParentId(2);
  Assert.assertEquals(2,inode1.getParentId());
}
