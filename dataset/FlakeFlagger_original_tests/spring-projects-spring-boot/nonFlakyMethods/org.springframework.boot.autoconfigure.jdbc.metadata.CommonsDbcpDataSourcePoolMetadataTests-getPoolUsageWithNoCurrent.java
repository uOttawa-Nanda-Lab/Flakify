@Test public void getPoolUsageWithNoCurrent(){
  CommonsDbcpDataSourcePoolMetadata dsm=new CommonsDbcpDataSourcePoolMetadata(createDataSource()){
    @Override public Integer getActive(){
      return null;
    }
  }
;
  assertNull(dsm.getUsage());
}
