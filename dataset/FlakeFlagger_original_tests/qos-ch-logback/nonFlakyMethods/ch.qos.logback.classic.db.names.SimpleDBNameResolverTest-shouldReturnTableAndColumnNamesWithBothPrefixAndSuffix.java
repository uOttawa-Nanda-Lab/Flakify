@Test public void shouldReturnTableAndColumnNamesWithBothPrefixAndSuffix() throws Exception {
  nameResolver.setTableNamePrefix("tp_");
  nameResolver.setTableNameSuffix("_ts");
  nameResolver.setColumnNamePrefix("cp_");
  nameResolver.setColumnNameSuffix("_cs");
  assertThat(nameResolver.getTableName(TableName.LOGGING_EVENT)).isEqualTo("tp_logging_event_ts");
  assertThat(nameResolver.getColumnName(ColumnName.THREAD_NAME)).isEqualTo("cp_thread_name_cs");
}
