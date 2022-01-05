@Test public void setNameTest(){
  InodeFolder inode1=new InodeFolder("test1",1,0,System.currentTimeMillis());
  Assert.assertEquals("test1",inode1.getName());
  inode1.setName("test2");
  Assert.assertEquals("test2",inode1.getName());
}
