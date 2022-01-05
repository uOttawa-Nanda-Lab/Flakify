@Test public void shouldReturnTableNameWithSuffix() throws Exception {
  nameResolver.setTableNameSuffix("_ts");
  assertThat(nameResolver.getTableName(TableName.LOGGING_EVENT)).isEqualTo("logging_event_ts");
  assertThat(nameResolver.getColumnName(ColumnName.THREAD_NAME)).isEqualTo("thread_name");
}
