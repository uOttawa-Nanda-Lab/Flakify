@Test public void testCreateTable() throws IOException {
  HTableDescriptor[] tables=admin.listTables();
  int numTables=tables.length;
  TEST_UTIL.createTable(Bytes.toBytes("testCreateTable"),HConstants.CATALOG_FAMILY);
  tables=this.admin.listTables();
  assertEquals(numTables + 1,tables.length);
}
