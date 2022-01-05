@Test public void equalsTest(){
  InodeFolder inode1=new InodeFolder("test1",1,0,System.currentTimeMillis());
  InodeFolder inode2=new InodeFolder("test2",1,0,System.currentTimeMillis());
  Assert.assertTrue(inode1.equals(inode2));
}
