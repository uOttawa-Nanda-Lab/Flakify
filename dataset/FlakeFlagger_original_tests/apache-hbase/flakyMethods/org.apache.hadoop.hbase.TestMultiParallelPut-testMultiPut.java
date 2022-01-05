public void testMultiPut() throws Exception {
  HTable table=new HTable(TEST_TABLE);
  table.setAutoFlush(false);
  table.setWriteBufferSize(10 * 1024 * 1024);
  for (  byte[] k : keys) {
    Put put=new Put(k);
    put.add(BYTES_FAMILY,QUALIFIER,VALUE);
    table.put(put);
  }
  table.flushCommits();
  for (  byte[] k : keys) {
    Get get=new Get(k);
    get.addColumn(BYTES_FAMILY,QUALIFIER);
    Result r=table.get(get);
    assertTrue(r.containsColumn(BYTES_FAMILY,QUALIFIER));
    assertEquals(0,Bytes.compareTo(VALUE,r.getValue(BYTES_FAMILY,QUALIFIER)));
  }
  HBaseAdmin admin=new HBaseAdmin(conf);
  ClusterStatus cs=admin.getClusterStatus();
  assertEquals(2,cs.getServers());
  for (  HServerInfo info : cs.getServerInfo()) {
    System.out.println(info);
    assertTrue(info.getLoad().getNumberOfRegions() > 10);
  }
}
