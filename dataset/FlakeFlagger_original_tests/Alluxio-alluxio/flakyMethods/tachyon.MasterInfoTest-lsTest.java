@Test public void lsTest() throws FileAlreadyExistException, InvalidPathException, TachyonException, BlockInfoException, FileDoesNotExistException {
  for (int i=0; i < 10; i++) {
    mMasterInfo.mkdir("/i" + i);
    for (int j=0; j < 10; j++) {
      mMasterInfo.createFile("/i" + i + "/j"+ j,64);
    }
  }
  Assert.assertEquals(1,mMasterInfo.ls("/i0/j0",false).size());
  Assert.assertEquals(1,mMasterInfo.ls("/i0/j0",true).size());
  for (int i=0; i < 10; i++) {
    Assert.assertEquals(11,mMasterInfo.ls("/i" + i,false).size());
    Assert.assertEquals(11,mMasterInfo.ls("/i" + i,true).size());
  }
  Assert.assertEquals(11,mMasterInfo.ls("/",false).size());
  Assert.assertEquals(111,mMasterInfo.ls("/",true).size());
}
