@Test public void shouldReturnDefaultSqlInsertLoggingEventQuery() throws Exception {
  DBNameResolver nameResolver=new DefaultDBNameResolver();
  String sql=SQLBuilder.buildInsertSQL(nameResolver);
  final String expected="INSERT INTO logging_event (timestmp, formatted_message, logger_name, level_string, thread_name, reference_flag, arg0, arg1, arg2, arg3, caller_filename, caller_class, caller_method, caller_line) VALUES (?, ?, ? ,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
  assertThat(sql).isEqualTo(expected);
}
