@Test public void isDirectoryTest(){
  InodeFolder inode1=new InodeFolder("test1",1,0,System.currentTimeMillis());
  Assert.assertTrue(inode1.isDirectory());
}
