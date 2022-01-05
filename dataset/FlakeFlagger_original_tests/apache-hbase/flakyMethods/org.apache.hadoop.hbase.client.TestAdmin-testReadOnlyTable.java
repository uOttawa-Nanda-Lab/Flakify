/** 
 * Test read only tables
 * @throws Exception
 */
@Test public void testReadOnlyTable() throws Exception {
  byte[] name=Bytes.toBytes("testReadOnlyTable");
  HTable table=TEST_UTIL.createTable(name,HConstants.CATALOG_FAMILY);
  byte[] value=Bytes.toBytes("somedata");
  Put put=new Put(value);
  put.add(HConstants.CATALOG_FAMILY,HConstants.CATALOG_FAMILY,value);
  table.put(put);
}
