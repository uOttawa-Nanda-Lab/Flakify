@Test public void getPoolUsageWithUnlimitedPool(){
  DataSourcePoolMetadata unlimitedDataSource=createDataSourceMetadata(0,-1);
  assertEquals(Float.valueOf(-1F),unlimitedDataSource.getUsage());
}
