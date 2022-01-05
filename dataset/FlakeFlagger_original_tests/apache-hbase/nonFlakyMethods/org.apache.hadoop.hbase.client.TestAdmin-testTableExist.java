@Test public void testTableExist() throws IOException {
  final byte[] table=Bytes.toBytes("testTableExist");
  boolean exist=false;
  exist=this.admin.tableExists(table);
  assertEquals(false,exist);
  TEST_UTIL.createTable(table,HConstants.CATALOG_FAMILY);
  exist=this.admin.tableExists(table);
  assertEquals(true,exist);
}
