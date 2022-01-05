@Test public void driverClassNameFromDriverClassNamePropertyWhenDefined(){
  DataSourceProperties configuration=new DataSourceProperties();
  configuration.setUrl("jdbc:mysql://mydb");
  configuration.setDriverClassName("org.hsqldb.jdbcDriver");
  String driverClassName=configuration.getDriverClassName();
  assertEquals(driverClassName,"org.hsqldb.jdbcDriver");
}
