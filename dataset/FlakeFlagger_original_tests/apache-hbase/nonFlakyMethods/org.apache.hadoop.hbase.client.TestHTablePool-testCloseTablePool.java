@Test public void testCloseTablePool() throws IOException {
  HTablePool pool=new HTablePool(TEST_UTIL.getConfiguration(),4);
  String tableName="testTable";
  HBaseAdmin admin=new HBaseAdmin(TEST_UTIL.getConfiguration());
  if (admin.tableExists(tableName)) {
    admin.deleteTable(tableName);
  }
  HTableDescriptor tableDescriptor=new HTableDescriptor(Bytes.toBytes(tableName));
  tableDescriptor.addFamily(new HColumnDescriptor("randomFamily"));
  admin.createTable(tableDescriptor);
  HTableInterface[] tables=new HTableInterface[4];
  for (int i=0; i < 4; ++i) {
    tables[i]=pool.getTable(tableName);
  }
  pool.closeTablePool(tableName);
  for (int i=0; i < 4; ++i) {
    pool.putTable(tables[i]);
  }
  Assert.assertEquals(4,pool.getCurrentPoolSize(tableName));
  pool.closeTablePool(tableName);
  Assert.assertEquals(0,pool.getCurrentPoolSize(tableName));
}
