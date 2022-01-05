@Test public void getColumnsTest() throws IOException {
  for (int k=1; k < Constants.MAX_COLUMNS; k+=Constants.MAX_COLUMNS / 5) {
    int fileId=mTfs.createRawTable("/table" + k,k);
    RawTable table=mTfs.getRawTable(fileId);
    Assert.assertEquals(k,table.getColumns());
    table=mTfs.getRawTable("/table" + k);
    Assert.assertEquals(k,table.getColumns());
    fileId=mTfs.createRawTable("/tabl" + k,k,TestUtils.getIncreasingByteBuffer(k % 10));
    table=mTfs.getRawTable(fileId);
    Assert.assertEquals(k,table.getColumns());
    table=mTfs.getRawTable("/tabl" + k);
    Assert.assertEquals(k,table.getColumns());
  }
}
