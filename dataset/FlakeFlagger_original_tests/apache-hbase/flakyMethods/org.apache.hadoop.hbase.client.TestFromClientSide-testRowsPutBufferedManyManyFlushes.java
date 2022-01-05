@Test public void testRowsPutBufferedManyManyFlushes() throws IOException {
  final byte[] CONTENTS_FAMILY=Bytes.toBytes("contents");
  final byte[] SMALL_FAMILY=Bytes.toBytes("smallfam");
  final byte[] value=Bytes.toBytes("abcd");
  final int NB_BATCH_ROWS=10;
  HTable table=TEST_UTIL.createTable(Bytes.toBytes("testRowsPutBufferedManyManyFlushes"),new byte[][]{CONTENTS_FAMILY,SMALL_FAMILY});
  table.setAutoFlush(false);
  table.setWriteBufferSize(10);
  ArrayList<Put> rowsUpdate=new ArrayList<Put>();
  for (int i=0; i < NB_BATCH_ROWS * 10; i++) {
    byte[] row=Bytes.toBytes("row" + i);
    Put put=new Put(row);
    put.add(CONTENTS_FAMILY,null,value);
    rowsUpdate.add(put);
  }
  table.put(rowsUpdate);
  table.flushCommits();
  Scan scan=new Scan();
  scan.addFamily(CONTENTS_FAMILY);
  ResultScanner scanner=table.getScanner(scan);
  int nbRows=0;
  for (  @SuppressWarnings("unused") Result row : scanner)   nbRows++;
  assertEquals(NB_BATCH_ROWS * 10,nbRows);
}
