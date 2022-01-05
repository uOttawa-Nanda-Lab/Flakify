@Test public void testRowsPut() throws IOException {
  final byte[] CONTENTS_FAMILY=Bytes.toBytes("contents");
  final byte[] SMALL_FAMILY=Bytes.toBytes("smallfam");
  final int NB_BATCH_ROWS=10;
  final byte[] value=Bytes.toBytes("abcd");
  HTable table=TEST_UTIL.createTable(Bytes.toBytes("testRowsPut"),new byte[][]{CONTENTS_FAMILY,SMALL_FAMILY});
  ArrayList<Put> rowsUpdate=new ArrayList<Put>();
  for (int i=0; i < NB_BATCH_ROWS; i++) {
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
  assertEquals(NB_BATCH_ROWS,nbRows);
}
