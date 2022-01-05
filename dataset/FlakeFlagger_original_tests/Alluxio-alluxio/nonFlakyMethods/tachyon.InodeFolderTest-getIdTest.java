@Test public void getIdTest(){
  InodeFolder inode1=new InodeFolder("test1",1,0,System.currentTimeMillis());
  Assert.assertEquals(1,inode1.getId());
}
