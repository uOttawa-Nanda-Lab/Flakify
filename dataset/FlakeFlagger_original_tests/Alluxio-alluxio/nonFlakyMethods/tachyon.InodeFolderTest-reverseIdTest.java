@Test public void reverseIdTest(){
  InodeFolder inode1=new InodeFolder("test1",1,0,System.currentTimeMillis());
  inode1.reverseId();
  Assert.assertEquals(-1,inode1.getId());
}
