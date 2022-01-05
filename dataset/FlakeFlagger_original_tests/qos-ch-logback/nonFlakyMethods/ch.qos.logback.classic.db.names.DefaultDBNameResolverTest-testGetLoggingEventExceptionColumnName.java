@Test public void testGetLoggingEventExceptionColumnName() throws Exception {
  String columnName=resolver.getColumnName(ColumnName.TRACE_LINE);
  assertThat(columnName).isEqualTo("trace_line");
}
