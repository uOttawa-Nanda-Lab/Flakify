@Test public void isFileTest() throws TachyonException {
  InodeRawTable inode1=new InodeRawTable("test1",1,0,10,null,System.currentTimeMillis());
  Assert.assertFalse(inode1.isFile());
}
