@Test public void getPoolUsageWithNoMax(){
  CommonsDbcpDataSourcePoolMetadata dsm=new CommonsDbcpDataSourcePoolMetadata(createDataSource()){
    @Override public Integer getMax(){
      return null;
    }
  }
;
  assertNull(dsm.getUsage());
}
