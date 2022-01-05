@Test public void testRegionServerSessionExpired() throws Exception {
  LOG.info("Starting testRegionServerSessionExpired");
  new HTable(conf,HConstants.META_TABLE_NAME);
  TEST_UTIL.getMiniHBaseCluster().getRegionServer(0).getConfiguration().setBoolean("hbase.regionserver.restart.on.zk.expire",true);
  TEST_UTIL.expireRegionServerSession(0);
  testSanity();
}
