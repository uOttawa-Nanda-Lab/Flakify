public void testHBaseMiniCluster() throws IOException {
  assertNotNull(hbaseCluster);
  assertNotNull(new HTable(conf,HConstants.META_TABLE_NAME));
}
