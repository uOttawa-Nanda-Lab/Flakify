@Test public void testGetLoggingEventColumnName() throws Exception {
  String columnName=resolver.getColumnName(ColumnName.LOGGER_NAME);
  assertThat(columnName).isEqualTo("logger_name");
}
