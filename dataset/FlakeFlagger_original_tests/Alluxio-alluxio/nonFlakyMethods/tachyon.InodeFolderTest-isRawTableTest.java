@Test public void isRawTableTest(){
  InodeFolder inodeFolder=new InodeFolder("testFolder1",1,0,System.currentTimeMillis());
  InodeFolder inodeRawTable=new InodeFolder("testRawTable1",2,0,InodeType.RawTable,System.currentTimeMillis());
  Assert.assertFalse(inodeFolder.isRawTable());
  Assert.assertTrue(inodeRawTable.isRawTable());
}
