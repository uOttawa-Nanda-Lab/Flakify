@Test public void setPinTest(){
  InodeFile inodeFile=new InodeFile("testFile1",1,0,1000,System.currentTimeMillis());
  Assert.assertFalse(inodeFile.isPin());
  inodeFile.setPin(true);
  Assert.assertTrue(inodeFile.isPin());
  inodeFile.setPin(false);
  Assert.assertFalse(inodeFile.isPin());
}
