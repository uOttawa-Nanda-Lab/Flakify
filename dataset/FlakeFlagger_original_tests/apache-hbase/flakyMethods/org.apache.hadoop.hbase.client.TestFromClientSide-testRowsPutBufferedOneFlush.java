@Test public void testRowsPutBufferedOneFlush() throws IOException {
  final byte[] CONTENTS_FAMILY=Bytes.toBytes("contents");
  final byte[] SMALL_FAMILY=Bytes.toBytes("smallfam");
  final byte[] value=Bytes.toBytes("abcd");
  final int NB_BATCH_ROWS=10;
  HTable table=TEST_UTIL.createTable(Bytes.toBytes("testRowsPutBufferedOneFlush"),new byte[][]{CONTENTS_FAMILY,SMALL_FAMILY});
  table.setAutoFlush(false);
  ArrayList<Put> rowsUpdate=new ArrayList<Put>();
  for (int i=0; i < NB_BATCH_ROWS * 10; i++) {
    byte[] row=Bytes.toBytes("row" + i);
    Put put=new Put(row);
    put.add(CONTENTS_FAMILY,null,value);
    rowsUpdate.add(put);
  }
  table.put(rowsUpdate);
  Scan scan=new Scan();
  scan.addFamily(CONTENTS_FAMILY);
  ResultScanner scanner=table.getScanner(scan);
  int nbRows=0;
  for (  @SuppressWarnings("unused") Result row : scanner)   nbRows++;
  assertEquals(0,nbRows);
  scanner.close();
  table.flushCommits();
  scan=new Scan();
  scan.addFamily(CONTENTS_FAMILY);
  scanner=table.getScanner(scan);
  nbRows=0;
  for (  @SuppressWarnings("unused") Result row : scanner)   nbRows++;
  assertEquals(NB_BATCH_ROWS * 10,nbRows);
}
