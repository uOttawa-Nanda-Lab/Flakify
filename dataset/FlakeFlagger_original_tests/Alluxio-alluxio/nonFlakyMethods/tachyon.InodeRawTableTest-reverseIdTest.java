@Test public void reverseIdTest() throws TachyonException {
  InodeRawTable inode1=new InodeRawTable("test1",1,0,10,null,System.currentTimeMillis());
  inode1.reverseId();
  Assert.assertEquals(-1,inode1.getId());
}
