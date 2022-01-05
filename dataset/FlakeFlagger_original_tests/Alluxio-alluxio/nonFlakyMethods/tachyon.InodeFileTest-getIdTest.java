@Test public void getIdTest(){
  InodeFile inode1=new InodeFile("test1",1,0,1000,System.currentTimeMillis());
  Assert.assertEquals(1,inode1.getId());
}
