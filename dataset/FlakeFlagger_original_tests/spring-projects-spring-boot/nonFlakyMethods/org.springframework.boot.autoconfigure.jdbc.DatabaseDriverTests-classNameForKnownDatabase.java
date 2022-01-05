@Test public void classNameForKnownDatabase(){
  String driverClassName=DatabaseDriver.fromJdbcUrl("jdbc:postgresql://hostname/dbname").getDriverClassName();
  assertEquals("org.postgresql.Driver",driverClassName);
}
