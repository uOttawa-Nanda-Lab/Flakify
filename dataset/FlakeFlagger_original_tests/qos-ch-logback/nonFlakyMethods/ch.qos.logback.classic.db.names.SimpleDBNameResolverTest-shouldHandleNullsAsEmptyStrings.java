@Test public void shouldHandleNullsAsEmptyStrings() throws Exception {
  nameResolver.setTableNamePrefix(null);
  nameResolver.setTableNameSuffix(null);
  nameResolver.setColumnNamePrefix(null);
  nameResolver.setColumnNameSuffix(null);
  assertThat(nameResolver.getTableName(TableName.LOGGING_EVENT)).isEqualTo("logging_event");
  assertThat(nameResolver.getColumnName(ColumnName.THREAD_NAME)).isEqualTo("thread_name");
}
