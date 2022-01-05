@Override public void getValidationQuery(){
  HikariDataSource dataSource=createDataSource(0,4);
  dataSource.setConnectionTestQuery("SELECT FROM FOO");
  assertEquals("SELECT FROM FOO",new HikariDataSourcePoolMetadata(dataSource).getValidationQuery());
}
