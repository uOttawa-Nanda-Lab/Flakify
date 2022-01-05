@Test public void createRawTableTestWithMetadata() throws IOException {
  int fileId=mTfs.createRawTable("/tables/table1",20,TestUtils.getIncreasingByteBuffer(9));
  RawTable table=mTfs.getRawTable(fileId);
  Assert.assertEquals(fileId,table.getId());
  Assert.assertEquals("/tables/table1",table.getPath());
  Assert.assertEquals(20,table.getColumns());
  Assert.assertEquals(TestUtils.getIncreasingByteBuffer(9),table.getMetadata());
  table=mTfs.getRawTable("/tables/table1");
  Assert.assertEquals(fileId,table.getId());
  Assert.assertEquals("/tables/table1",table.getPath());
  Assert.assertEquals(20,table.getColumns());
  Assert.assertEquals(TestUtils.getIncreasingByteBuffer(9),table.getMetadata());
}
