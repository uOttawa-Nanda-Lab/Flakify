@Test public void isDirectoryTest(){
  InodeFile inode1=new InodeFile("test1",1,0,1000,System.currentTimeMillis());
  Assert.assertFalse(inode1.isDirectory());
}
