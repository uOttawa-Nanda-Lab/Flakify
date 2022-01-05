@Test public void isDirectoryTest() throws TachyonException {
  InodeRawTable inode1=new InodeRawTable("test1",1,0,10,null,System.currentTimeMillis());
  Assert.assertTrue(inode1.isDirectory());
}
