@Test public void getIdTest() throws IOException {
  for (int k=1; k < Constants.MAX_COLUMNS; k+=Constants.MAX_COLUMNS / 5) {
    int fileId=mTfs.createRawTable("/table" + k,1);
    RawTable table=mTfs.getRawTable(fileId);
    Assert.assertEquals(fileId,table.getId());
    table=mTfs.getRawTable("/table" + k);
    Assert.assertEquals(fileId,table.getId());
    fileId=mTfs.createRawTable("/tabl" + k,1,TestUtils.getIncreasingByteBuffer(k % 10));
    table=mTfs.getRawTable(fileId);
    Assert.assertEquals(fileId,table.getId());
    table=mTfs.getRawTable("/tabl" + k);
    Assert.assertEquals(fileId,table.getId());
  }
}
