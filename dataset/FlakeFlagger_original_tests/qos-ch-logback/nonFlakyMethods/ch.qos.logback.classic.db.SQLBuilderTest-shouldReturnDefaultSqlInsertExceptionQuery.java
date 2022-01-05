@Test public void shouldReturnDefaultSqlInsertExceptionQuery() throws Exception {
  DBNameResolver nameResolver=new DefaultDBNameResolver();
  String sql=SQLBuilder.buildInsertExceptionSQL(nameResolver);
  final String expected="INSERT INTO logging_event_exception (event_id, i, trace_line) VALUES (?, ?, ?)";
  assertThat(sql).isEqualTo(expected);
}
