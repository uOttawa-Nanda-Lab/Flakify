@Test public void failureOnMalformedJdbcUrl(){
  this.thrown.expect(IllegalArgumentException.class);
  this.thrown.expectMessage("URL must start with");
  DatabaseDriver.fromJdbcUrl("malformed:url");
}
