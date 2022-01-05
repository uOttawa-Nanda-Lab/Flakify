@Test public void updateMetadataTest() throws IOException {
  for (int k=1; k < Constants.MAX_COLUMNS; k+=Constants.MAX_COLUMNS / 5) {
    int fileId=mTfs.createRawTable("/x/table" + k,1);
    RawTable table=mTfs.getRawTable(fileId);
    table.updateMetadata(TestUtils.getIncreasingByteBuffer(k % 17));
    Assert.assertEquals(TestUtils.getIncreasingByteBuffer(k % 17),table.getMetadata());
    table=mTfs.getRawTable("/x/table" + k);
    Assert.assertEquals(TestUtils.getIncreasingByteBuffer(k % 17),table.getMetadata());
    fileId=mTfs.createRawTable("/y/tab" + k,1,TestUtils.getIncreasingByteBuffer(k % 7));
    table=mTfs.getRawTable(fileId);
    table.updateMetadata(TestUtils.getIncreasingByteBuffer(k % 16));
    Assert.assertEquals(TestUtils.getIncreasingByteBuffer(k % 16),table.getMetadata());
    table=mTfs.getRawTable("/y/tab" + k);
    Assert.assertEquals(TestUtils.getIncreasingByteBuffer(k % 16),table.getMetadata());
  }
}
