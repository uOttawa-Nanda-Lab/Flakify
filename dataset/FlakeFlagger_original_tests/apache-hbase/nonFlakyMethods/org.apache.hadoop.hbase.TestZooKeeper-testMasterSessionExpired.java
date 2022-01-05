@Test public void testMasterSessionExpired() throws Exception {
  LOG.info("Starting testRegionServerSessionExpired");
  new HTable(conf,HConstants.META_TABLE_NAME);
  TEST_UTIL.expireMasterSession();
  testSanity();
}
