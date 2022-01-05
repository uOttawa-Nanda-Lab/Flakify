@Test public void testGetTableName() throws Exception {
  String tableName=resolver.getTableName(TableName.LOGGING_EVENT_EXCEPTION);
  assertThat(tableName).isEqualTo("logging_event_exception");
}
