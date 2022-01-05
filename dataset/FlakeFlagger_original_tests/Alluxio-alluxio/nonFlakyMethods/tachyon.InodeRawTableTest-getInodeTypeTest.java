@Test public void getInodeTypeTest() throws TachyonException {
  InodeRawTable inode1=new InodeRawTable("test1",1,0,10,null,System.currentTimeMillis());
  Assert.assertEquals(inode1.getInodeType(),InodeType.RawTable);
}
