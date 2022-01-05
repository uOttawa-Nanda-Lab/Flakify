@Test public void setNameTest(){
  InodeFile inode1=new InodeFile("test1",1,0,1000,System.currentTimeMillis());
  Assert.assertEquals("test1",inode1.getName());
  inode1.setName("test2");
  Assert.assertEquals("test2",inode1.getName());
}
