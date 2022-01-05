@Test public void testGet_EmptyTable() throws IOException {
  HTable table=TEST_UTIL.createTable(Bytes.toBytes("testGet_EmptyTable"),FAMILY);
  Get get=new Get(ROW);
  get.addFamily(FAMILY);
  Result r=table.get(get);
  assertTrue(r.isEmpty());
}
