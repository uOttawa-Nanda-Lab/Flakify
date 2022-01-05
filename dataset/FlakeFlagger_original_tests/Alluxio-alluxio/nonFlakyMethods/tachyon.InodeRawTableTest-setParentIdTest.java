@Test public void setParentIdTest() throws TachyonException {
  InodeRawTable inode1=new InodeRawTable("test1",1,0,10,null,System.currentTimeMillis());
  Assert.assertEquals(0,inode1.getParentId());
  inode1.setParentId(2);
  Assert.assertEquals(2,inode1.getParentId());
}
