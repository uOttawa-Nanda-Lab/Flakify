@Override public void getValidationQuery(){
  DataSource dataSource=createDataSource(0,4);
  dataSource.setValidationQuery("SELECT FROM FOO");
  assertEquals("SELECT FROM FOO",new TomcatDataSourcePoolMetadata(dataSource).getValidationQuery());
}
