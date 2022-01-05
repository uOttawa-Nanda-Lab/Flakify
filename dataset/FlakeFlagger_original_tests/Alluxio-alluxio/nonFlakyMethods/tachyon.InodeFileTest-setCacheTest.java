@Test public void setCacheTest(){
  InodeFile inodeFile=new InodeFile("testFile1",1,0,1000,System.currentTimeMillis());
  Assert.assertFalse(inodeFile.isCache());
  inodeFile.setCache(true);
  Assert.assertTrue(inodeFile.isCache());
  inodeFile.setCache(false);
  Assert.assertFalse(inodeFile.isCache());
}
