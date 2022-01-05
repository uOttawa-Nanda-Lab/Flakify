@Test public void testEnableDisableAddColumnDeleteColumn() throws Exception {
  byte[] tableName=Bytes.toBytes("testMasterAdmin");
  TEST_UTIL.createTable(tableName,HConstants.CATALOG_FAMILY);
  this.admin.disableTable(tableName);
  try {
    new HTable(TEST_UTIL.getConfiguration(),tableName);
  }
 catch (  org.apache.hadoop.hbase.client.RegionOfflineException e) {
  }
  this.admin.addColumn(tableName,new HColumnDescriptor("col2"));
  this.admin.enableTable(tableName);
  try {
    this.admin.deleteColumn(tableName,Bytes.toBytes("col2"));
  }
 catch (  TableNotDisabledException e) {
  }
  this.admin.disableTable(tableName);
  this.admin.deleteColumn(tableName,Bytes.toBytes("col2"));
  this.admin.deleteTable(tableName);
}
