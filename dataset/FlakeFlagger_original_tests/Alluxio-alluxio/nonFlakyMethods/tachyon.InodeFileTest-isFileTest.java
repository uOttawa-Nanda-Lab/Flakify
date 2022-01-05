@Test public void isFileTest(){
  InodeFile inode1=new InodeFile("test1",1,0,1000,System.currentTimeMillis());
  Assert.assertTrue(inode1.isFile());
}
