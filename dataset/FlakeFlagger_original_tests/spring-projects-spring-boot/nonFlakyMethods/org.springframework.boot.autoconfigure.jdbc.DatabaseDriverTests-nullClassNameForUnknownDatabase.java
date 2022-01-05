@Test public void nullClassNameForUnknownDatabase(){
  String driverClassName=DatabaseDriver.fromJdbcUrl("jdbc:unknowndb://hostname/dbname").getDriverClassName();
  assertNull(driverClassName);
}
