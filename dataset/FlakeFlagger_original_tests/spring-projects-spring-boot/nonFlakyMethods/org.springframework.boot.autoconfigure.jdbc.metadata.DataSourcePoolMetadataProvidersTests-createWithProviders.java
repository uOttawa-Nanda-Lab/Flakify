@Test public void createWithProviders(){
  DataSourcePoolMetadataProviders provider=new DataSourcePoolMetadataProviders(Arrays.asList(this.firstProvider,this.secondProvider));
  assertSame(this.first,provider.getDataSourcePoolMetadata(this.firstDataSource));
  assertSame(this.second,provider.getDataSourcePoolMetadata(this.secondDataSource));
  assertNull(provider.getDataSourcePoolMetadata(this.unknownDataSource));
}
