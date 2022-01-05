@Test public void shouldReturnSimpleSqlInsertExceptionQuery() throws Exception {
  DBNameResolver nameResolver=createSimpleDBNameResolver();
  String sql=SQLBuilder.buildInsertExceptionSQL(nameResolver);
  final String expected="INSERT INTO tp_logging_event_exception_ts (cp_event_id_cs, cp_i_cs, cp_trace_line_cs) VALUES (?, ?, ?)";
  assertThat(sql).isEqualTo(expected);
}
