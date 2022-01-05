@Test public void isFileTest(){
  InodeFolder inode1=new InodeFolder("test1",1,0,System.currentTimeMillis());
  Assert.assertFalse(inode1.isFile());
}
