@Test public void comparableTest(){
  InodeFile inode1=new InodeFile("test1",1,0,1000,System.currentTimeMillis());
  InodeFile inode2=new InodeFile("test2",2,0,1000,System.currentTimeMillis());
  Assert.assertEquals(-1,inode1.compareTo(inode2));
  Assert.assertEquals(0,inode1.compareTo(inode1));
  Assert.assertEquals(0,inode2.compareTo(inode2));
  Assert.assertEquals(1,inode2.compareTo(inode1));
}
