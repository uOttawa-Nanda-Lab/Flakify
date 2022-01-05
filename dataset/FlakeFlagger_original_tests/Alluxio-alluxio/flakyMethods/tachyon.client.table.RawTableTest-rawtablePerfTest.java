@Test public void rawtablePerfTest() throws IOException {
  int col=200;
  long sMs=System.currentTimeMillis();
  int fileId=mTfs.createRawTable("/table",col);
  sMs=System.currentTimeMillis();
  RawTable table=mTfs.getRawTable(fileId);
  Assert.assertEquals(col,table.getColumns());
  table=mTfs.getRawTable("/table");
  Assert.assertEquals(col,table.getColumns());
  sMs=System.currentTimeMillis();
  for (int k=0; k < col; k++) {
    RawColumn rawCol=table.getRawColumn(k);
    rawCol.createPartition(0);
    TachyonFile file=rawCol.getPartition(0);
    OutStream outStream=file.getOutStream(WriteType.MUST_CACHE);
    outStream.write(TestUtils.getIncreasingByteArray(10));
    outStream.close();
  }
  sMs=System.currentTimeMillis();
  for (int k=0; k < col; k++) {
    RawColumn rawCol=table.getRawColumn(k);
    TachyonFile file=rawCol.getPartition(0,true);
    TachyonByteBuffer buf=file.readByteBuffer();
    Assert.assertEquals(TestUtils.getIncreasingByteBuffer(10),buf.DATA);
    buf.close();
  }
  sMs=System.currentTimeMillis();
  for (int k=0; k < col; k++) {
    RawColumn rawCol=table.getRawColumn(k);
    TachyonFile file=rawCol.getPartition(0,true);
    TachyonByteBuffer buf=file.readByteBuffer();
    Assert.assertEquals(TestUtils.getIncreasingByteBuffer(10),buf.DATA);
    buf.close();
  }
}
