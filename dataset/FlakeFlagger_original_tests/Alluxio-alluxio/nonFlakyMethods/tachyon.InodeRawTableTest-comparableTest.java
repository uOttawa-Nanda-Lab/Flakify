@Test public void comparableTest() throws TachyonException {
  InodeRawTable inode1=new InodeRawTable("test1",1,0,10,null,System.currentTimeMillis());
  InodeRawTable inode2=new InodeRawTable("test2",2,0,10,null,System.currentTimeMillis());
  Assert.assertEquals(-1,inode1.compareTo(inode2));
  Assert.assertEquals(0,inode1.compareTo(inode1));
  Assert.assertEquals(0,inode2.compareTo(inode2));
  Assert.assertEquals(1,inode2.compareTo(inode1));
}
