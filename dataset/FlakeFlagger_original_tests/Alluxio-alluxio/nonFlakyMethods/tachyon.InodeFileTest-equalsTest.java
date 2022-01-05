@Test public void equalsTest(){
  InodeFile inode1=new InodeFile("test1",1,0,1000,System.currentTimeMillis());
  InodeFile inode2=new InodeFile("test2",1,0,1000,System.currentTimeMillis());
  Assert.assertTrue(inode1.equals(inode2));
}
