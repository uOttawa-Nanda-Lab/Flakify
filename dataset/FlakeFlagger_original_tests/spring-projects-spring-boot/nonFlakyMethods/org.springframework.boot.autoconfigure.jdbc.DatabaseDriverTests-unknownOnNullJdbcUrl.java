@Test public void unknownOnNullJdbcUrl(){
  assertThat(DatabaseDriver.fromJdbcUrl(null),equalTo(DatabaseDriver.UNKNOWN));
}
