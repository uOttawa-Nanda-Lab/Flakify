@Test public void getPathTest() throws IOException {
  for (int k=1; k < Constants.MAX_COLUMNS; k+=Constants.MAX_COLUMNS / 5) {
    int fileId=mTfs.createRawTable("/x/table" + k,1);
    RawTable table=mTfs.getRawTable(fileId);
    Assert.assertEquals("/x/table" + k,table.getPath());
    table=mTfs.getRawTable("/x/table" + k);
    Assert.assertEquals("/x/table" + k,table.getPath());
    fileId=mTfs.createRawTable("/y/tab" + k,1,TestUtils.getIncreasingByteBuffer(k % 10));
    table=mTfs.getRawTable(fileId);
    Assert.assertEquals("/y/tab" + k,table.getPath());
    table=mTfs.getRawTable("/y/tab" + k);
    Assert.assertEquals("/y/tab" + k,table.getPath());
  }
}
