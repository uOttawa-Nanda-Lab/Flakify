@Test public void setParentIdTest(){
  InodeFolder inode1=new InodeFolder("test1",1,0,System.currentTimeMillis());
  Assert.assertEquals(0,inode1.getParentId());
  inode1.setParentId(2);
  Assert.assertEquals(2,inode1.getParentId());
}
