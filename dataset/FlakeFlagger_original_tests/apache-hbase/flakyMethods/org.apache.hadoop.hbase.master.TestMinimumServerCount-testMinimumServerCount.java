public void testMinimumServerCount() throws Exception {
  HBaseAdmin admin=new HBaseAdmin(conf);
  admin.createTable(createTableDescriptor(TABLE_NAME));
  admin.disableTable(TABLE_NAME);
  assertFalse(admin.isTableEnabled(TABLE_NAME));
  cluster.hbaseCluster.getMaster().getServerManager().setMinimumServerCount(2);
  try {
    admin.enableTable(TABLE_NAME);
  }
 catch (  IOException ex) {
  }
  Thread.sleep(10 * 1000);
  assertFalse(admin.isTableAvailable(TABLE_NAME));
  cluster.startRegionServer();
  Thread.sleep(10 * 1000);
  assertTrue(admin.isTableAvailable(TABLE_NAME));
}
