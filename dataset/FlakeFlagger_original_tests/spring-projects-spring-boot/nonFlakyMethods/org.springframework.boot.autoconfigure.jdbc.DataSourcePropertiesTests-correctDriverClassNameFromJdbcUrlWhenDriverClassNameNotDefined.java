@Test public void correctDriverClassNameFromJdbcUrlWhenDriverClassNameNotDefined(){
  DataSourceProperties configuration=new DataSourceProperties();
  configuration.setUrl("jdbc:mysql://mydb");
  String driverClassName=configuration.getDriverClassName();
  assertEquals(driverClassName,"com.mysql.jdbc.Driver");
}
