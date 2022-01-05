@Test public void shouldReturnTableNameWithBothPrefixAndSuffix() throws Exception {
  nameResolver.setTableNamePrefix("tp_");
  nameResolver.setTableNameSuffix("_ts");
  assertThat(nameResolver.getTableName(TableName.LOGGING_EVENT)).isEqualTo("tp_logging_event_ts");
  assertThat(nameResolver.getColumnName(ColumnName.THREAD_NAME)).isEqualTo("thread_name");
}
