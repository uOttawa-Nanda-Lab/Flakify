@Override public void getValidationQuery(){
  BasicDataSource dataSource=createDataSource();
  dataSource.setValidationQuery("SELECT FROM FOO");
  assertEquals("SELECT FROM FOO",new CommonsDbcpDataSourcePoolMetadata(dataSource).getValidationQuery());
}
