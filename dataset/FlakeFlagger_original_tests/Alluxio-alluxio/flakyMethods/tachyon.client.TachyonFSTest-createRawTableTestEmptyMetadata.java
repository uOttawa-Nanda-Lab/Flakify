@Test public void createRawTableTestEmptyMetadata() throws IOException {
  int fileId=mTfs.createRawTable("/tables/table1",20);
  RawTable table=mTfs.getRawTable(fileId);
  Assert.assertEquals(fileId,table.getId());
  Assert.assertEquals("/tables/table1",table.getPath());
  Assert.assertEquals(20,table.getColumns());
  Assert.assertEquals(ByteBuffer.allocate(0),table.getMetadata());
  table=mTfs.getRawTable("/tables/table1");
  Assert.assertEquals(fileId,table.getId());
  Assert.assertEquals("/tables/table1",table.getPath());
  Assert.assertEquals(20,table.getColumns());
  Assert.assertEquals(ByteBuffer.allocate(0),table.getMetadata());
}
