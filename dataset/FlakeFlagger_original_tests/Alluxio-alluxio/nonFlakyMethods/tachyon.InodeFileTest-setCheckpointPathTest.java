@Test public void setCheckpointPathTest(){
  InodeFile inodeFile=new InodeFile("testFile1",1,0,1000,System.currentTimeMillis());
  Assert.assertFalse(inodeFile.hasCheckpointed());
  Assert.assertEquals("",inodeFile.getCheckpointPath());
  inodeFile.setCheckpointPath("/testPath");
  Assert.assertEquals("/testPath",inodeFile.getCheckpointPath());
}
