@Test public void shouldReturnTableNameWithPrefix() throws Exception {
  nameResolver.setTableNamePrefix("tp_");
  assertThat(nameResolver.getTableName(TableName.LOGGING_EVENT)).isEqualTo("tp_logging_event");
  assertThat(nameResolver.getColumnName(ColumnName.THREAD_NAME)).isEqualTo("thread_name");
}
