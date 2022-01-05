@Test public void testGet_NonExistentRow() throws IOException {
  HTable table=TEST_UTIL.createTable(Bytes.toBytes("testGet_NonExistentRow"),FAMILY);
  Put put=new Put(ROW);
  put.add(FAMILY,QUALIFIER,VALUE);
  table.put(put);
  LOG.info("Row put");
  Get get=new Get(ROW);
  get.addFamily(FAMILY);
  Result r=table.get(get);
  assertFalse(r.isEmpty());
  System.out.println("Row retrieved successfully");
  byte[] missingrow=Bytes.toBytes("missingrow");
  get=new Get(missingrow);
  get.addFamily(FAMILY);
  r=table.get(get);
  assertTrue(r.isEmpty());
  LOG.info("Row missing as it should be");
}
